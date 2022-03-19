package google.android.svape.codelab_dogglers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import google.android.svape.codelab_dogglers.adapter.PaintersCardAdapter
import google.android.svape.codelab_dogglers.const.Layout
import google.android.svape.codelab_dogglers.databinding.ActivityGridListBinding
import google.android.svape.codelab_dogglers.databinding.ActivityVerticalListBinding

class VerticalListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerticalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerticalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.verticalRecyclerView.adapter = PaintersCardAdapter(
            applicationContext,
            Layout.VERTICAL
        )


        binding.verticalRecyclerView.setHasFixedSize(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}