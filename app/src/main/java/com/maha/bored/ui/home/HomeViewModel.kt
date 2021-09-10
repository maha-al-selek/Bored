package com.maha.bored.ui.home

import androidx.lifecycle.LiveData

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import com.maha.bored.models.BoredResponse
import com.maha.bored.repository.BoredRepository


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private var boredRepository: BoredRepository = BoredRepository()
    private var boredResponseLiveData: LiveData<BoredResponse> = boredRepository.getBoredResponseLiveDataLiveData()!!

    fun getActivities() {
        boredRepository.getActivity()
    }

    fun getVolumesResponseLiveData(): LiveData<BoredResponse> {
        return boredResponseLiveData
    }
}