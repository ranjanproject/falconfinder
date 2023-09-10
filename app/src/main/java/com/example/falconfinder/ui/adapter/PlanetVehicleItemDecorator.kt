package com.example.falconfinder.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.falconfinder.R

class PlanetVehicleItemDecorator(): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val space = view.context.resources.getDimension(R.dimen.margin_medium).toInt()
        outRect.bottom = space

    }
}