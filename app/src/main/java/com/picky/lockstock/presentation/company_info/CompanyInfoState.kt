package com.picky.lockstock.presentation.company_info

import com.picky.lockstock.domain.model.CompanyInfoModel
import com.picky.lockstock.domain.model.IntradayDataModel

data class CompanyInfoState(
    val stockInfos: List<IntradayDataModel> = emptyList(),
    val companyInfoModel: CompanyInfoModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {


}