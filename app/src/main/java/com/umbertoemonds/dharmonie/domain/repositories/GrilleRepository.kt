package com.umbertoemonds.dharmonie.domain.repositories

import com.umbertoemonds.dharmonie.data.models.Accord
import com.umbertoemonds.dharmonie.data.models.Grille

interface GrilleRepository {

    fun getGrilles(userToken: String, callback: GrilleCallback)

    fun getAccords(grilleId: Int, userToken: String, callback: AccordsCallback)

    interface GrilleCallback {
        fun onGrillesLoaded(grilles: List<Grille>)
        fun onSessionExpired()
        fun onError(t: Throwable)
    }

    interface AccordsCallback {
        fun onAccordLoaded(accords: List<Accord>)
        fun onSessionExpired()
        fun onError(t: Throwable)
    }

}