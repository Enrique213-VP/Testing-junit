package google.android.svape.codelab_dogglers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import google.android.svape.codelab_dogglers.adapter.PaintersCardAdapter
import google.android.svape.codelab_dogglers.const.Layout
import google.android.svape.codelab_dogglers.databinding.ActivityGridListBinding
import google.android.svape.codelab_dogglers.databinding.ActivityVerticalListBinding

class GridListActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityGridListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.gridRecyclerView.adapter = PaintersCardAdapter(
            applicationContext,
            Layout.GRID
        )


        binding.gridRecyclerView.setHasFixedSize(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}