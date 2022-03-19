package google.android.svape.codelab_dogglers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import google.android.svape.codelab_dogglers.adapter.PaintersCardAdapter
import google.android.svape.codelab_dogglers.const.Layout
import google.android.svape.codelab_dogglers.databinding.ActivityHorizontalListBinding

class HorizontalListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHorizontalListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorizontalListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.horizontalRecyclerView.adapter = PaintersCardAdapter(
            applicationContext,
            Layout.HORIZONTAL
        )


        binding.horizontalRecyclerView.setHasFixedSize(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}