package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.supportFunctions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.AreaItem


class AreaAdapter(context: Context, resource: Int, private val items: List<AreaItem>) :
    ArrayAdapter<AreaItem>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_spinner, parent, false)

        val textView = view.findViewById<TextView>(R.id.itemName)
        textView.text = items[position].areaName

        return view
    }
}