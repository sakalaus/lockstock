package com.picky.lockstock.presentation.company_info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.picky.lockstock.domain.model.IntradayDataModel

@Composable
fun IntradayDataTable(
    viewModel: CompanyInfoViewModel = hiltViewModel()
){
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ){
        items(state.stockInfos.size){ index ->
            val intradayDataModel = state.stockInfos[index]
            StockDataItem(
                intradayDataModel = intradayDataModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            if (index < state.stockInfos.size) {
                Divider(modifier = Modifier.padding(
                    horizontal = 16.dp
                ))
            }
        }
    }

}

@Composable
fun StockDataItem(
    intradayDataModel: IntradayDataModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
            ) {
                Text(
                    text = intradayDataModel.date.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = intradayDataModel.close.toString(),
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}
