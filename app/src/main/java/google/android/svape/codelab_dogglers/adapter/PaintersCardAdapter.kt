package google.android.svape.codelab_dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import google.android.svape.codelab_dogglers.R
import google.android.svape.codelab_dogglers.const.Layout
import google.android.svape.codelab_dogglers.data.DataSource
import google.android.svape.codelab_dogglers.data.DataSource.painters
import google.android.svape.codelab_dogglers.model.Painters
import java.nio.file.Files.size

class PaintersCardAdapter(private val context: Context?, private val layout: Int): RecyclerView.Adapter<PaintersCardAdapter.PaintersCardViewHolder>() {
    
    
    val PainterList = DataSource.painters


    class PaintersCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {


        val PainterImage: ImageView? = view?.findViewById(R.id.Image_list_item)
        val PainterName: TextView? = view?.findViewById(R.id.name_painter)
        val Painteryear: TextView? = view?.findViewById(R.id.years_in_the_world)
        val PainterArt: TextView? = view?.findViewById(R.id.artWork_more_famous)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaintersCardViewHolder {

        val adapterLayout = when (layout){
            Layout.GRID -> LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item, parent, false)
        else -> LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }
        return PaintersCardViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PaintersCardAdapter.PaintersCardViewHolder, position: Int) {

        val painterData = painters[position]
        holder.PainterImage?.setImageResource(painterData.imageResourceId)
        holder.PainterName?.text = painterData.name
        val resource = context?.resources
        holder.Painteryear?.text = resource?.getString(R.string.painter_years, painterData.years)
        holder.PainterArt?.text = resource?.getString(R.string.painter_artwork, painterData.artwork)
    }

    override fun getItemCount(): Int = PainterList.size
}