package com.ibtikar.apps.task.ui.profile

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.ibtikar.apps.task.R
import com.ibtikar.apps.task.pojo.ActorsProfiles
import com.ibtikar.apps.task.utils.ConstantsUtils
import com.squareup.picasso.Picasso


class GridAdapter(
    private val context: Activity,
    private val resource: Int,
    private val profileList: List<ActorsProfiles.Profile>

) : ArrayAdapter<ActorsProfiles.Profile>(context, resource, profileList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_gridview_layout, null, true)

        val profile = getItem(position)
        val img = rowView.findViewById(R.id.baseImgId) as ImageView

        Picasso.get()
            .load(ConstantsUtils.BASE_IMAGE_SOURCE + profile?.filePath)
            .into(img)

        return rowView
    }
}