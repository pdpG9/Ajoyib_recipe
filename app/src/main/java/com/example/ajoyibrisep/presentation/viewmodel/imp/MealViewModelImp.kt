package com.example.ajoyibrisep.presentation.viewmodel.imp

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajoyibrisep.data.db.entity.IngredientsModel
import com.example.ajoyibrisep.data.db.entity.MealModel
import com.example.ajoyibrisep.data.db.entity.PreparationModel
import com.example.ajoyibrisep.data.model.IngredientsData
import com.example.ajoyibrisep.domain.RecipeRepositoryImp
import com.example.ajoyibrisep.presentation.viewmodel.MealViewModel
import com.example.ajoyibrisep.utils.myLog
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MealViewModelImp : ViewModel(), MealViewModel {
    private var mealData: MealModel? = null
    private val repository = RecipeRepositoryImp.getInstance()
    override fun clickBack() {
        moveToBackLiveData.value = Unit
    }

    override fun clickLink() {

        moveToWebLiveData.value = Intent("android.intent.action.VIEW", Uri.parse(mealData?.web_url))
    }

    override fun loadCurrentMeal(mealId: Int) {
        showProgressLiveData.value = true
        if (mealId == -1) return
        viewModelScope.launch {
            repository.getMealById(mealId).collect {
                mealData = it
                "loadCurrentMeal:$it".myLog()
                var ingredientModel: IngredientsModel? = null
                var preparationModel: PreparationModel? = null
                it.let {
                    titleLiveData.value = it.title
                    getImages(mealId)
                    stateYouTubeButtonLiveData.value = it.video_uri != null
                    runBlocking {
                        repository.getIngredientsById(it.ingredients).collect { ing ->
                            ingredientModel = ing
                        }
                        repository.getPreparationsById(it.preparation).collect { pre ->
                            preparationModel = pre
                        }
                        actionsLiveData.value = IngredientsData(ingredientModel!!, preparationModel!!)
                    }
                }
                showProgressLiveData.value = false
            }
        }

    }

    private fun getImages(id: Int) {
        viewModelScope.launch {
            val list = ArrayList<String>()
            if (id != -1) {
                repository.getImagesById(id).collect {
                    it.apply {
                        if (this.image1 != null) list.add(this.image1)
                        if (this.image2 != null) list.add(this.image2)
                        if (this.image3 != null) list.add(this.image3)
                        if (this.image4 != null) list.add(this.image4)
                        if (this.image5 != null) list.add(this.image5)
                    }
                    imagesLiveData.postValue(list)
                }
            }
        }
    }

    override fun clickYouTube() {
        moveToYouTubeLiveData.value =
            Intent("android.intent.action.VIEW", Uri.parse(mealData?.video_uri))
    }

    override fun clickIngredients() {
        clickIngredientsLiveData.value = true
    }

    override fun clickActions() {
        clickIngredientsLiveData.value = false
    }

    override val showProgressLiveData = MutableLiveData<Boolean>()
    override val imagesLiveData = MutableLiveData<List<String>>()
    override val titleLiveData = MutableLiveData<String>()
    override val actionsLiveData = MutableLiveData<IngredientsData>()
    override val moveToYouTubeLiveData = MutableLiveData<Intent>()
    override val moveToWebLiveData = MutableLiveData<Intent>()
    override val moveToBackLiveData = MutableLiveData<Unit>()
    override val clickIngredientsLiveData = MutableLiveData<Boolean>()
    override val stateYouTubeButtonLiveData = MutableLiveData<Boolean>()
}