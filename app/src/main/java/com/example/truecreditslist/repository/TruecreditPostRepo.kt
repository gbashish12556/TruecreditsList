package com.example.truecreditslist.repository

import androidx.paging.PagingData
import com.example.truecreditslist.data.TruecreditsPost
import kotlinx.coroutines.flow.Flow


interface TruecreditPostRepo {
    fun postOfTrueCredits(pageNo: Int, pageSize: Int): Flow<PagingData<TruecreditsPost>>
}