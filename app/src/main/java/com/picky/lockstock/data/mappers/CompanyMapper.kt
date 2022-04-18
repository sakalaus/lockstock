package com.picky.lockstock.data.mappers

import com.picky.lockstock.data.local.CompanyListingEntity
import com.picky.lockstock.domain.model.CompanyListingModel

fun CompanyListingEntity.toCompanyListingModel() = CompanyListingModel(
    name = name,
    symbol = symbol,
    exchange = exchange
)

fun CompanyListingModel.toCompanyListingEntity() = CompanyListingEntity(
    name = name,
    symbol = symbol,
    exchange = exchange
)