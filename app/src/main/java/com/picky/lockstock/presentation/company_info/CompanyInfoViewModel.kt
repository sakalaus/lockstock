package com.picky.lockstock.presentation.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picky.lockstock.domain.repository.StockRepository
import com.picky.lockstock.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompanyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: StockRepository
) : ViewModel() {
    var state by mutableStateOf(CompanyInfoState())

    init {
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
            state = state.copy(isLoading = true)
            val companyInfoResult = async { repository.getCompanyInfo(symbol) }
            val intradayInfoResult = async { repository.getIntradayInfo(symbol) }
            when (val result = companyInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        companyInfoModel = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        companyInfoModel = null,
                        isLoading = false,
                        error = result.message
                    )
                }
                is Resource.Loading -> Unit
            }
            when (val result = intradayInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        stockInfos = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        stockInfos = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
                else-> Unit
            }
        }
    }

}