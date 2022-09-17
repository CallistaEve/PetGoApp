import Database.GlobalVar
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import Model.Animal

import com.uc.firstappsprogtech.databinding.CardHewanBinding
import firstappsprogtech.example.week1_0706012110036.InputAnimalScreen

class ListDataRVAdapter02(
    var data: MutableList<Animal>,
) : RecyclerView.Adapter<ListDataRVAdapter02.ViewHolder>() {

    inner class ViewHolder(val binding: CardHewanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardHewanBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            if(data.get(position).imageUri.isNotEmpty())
                binding.animaldisplay1ImageView.setImageURI(Uri.parse(data.get(position).imageUri))
                binding.namaHewan1TextView.text=data.get(position).nama
                binding.jenisHewan1TextView.text=data.get(position).jenis
                binding.usiaHewan1TextView.text="Usia : ".plus(data.get(position).usia)
                binding.editHewanButton.setOnClickListener {
                val myIntent = Intent(it.context, InputAnimalScreen::class.java)
                myIntent.putExtra("position", adapterPosition)
                it.context.startActivity(myIntent)
            }
            binding.deleteHewanButton.setOnClickListener {
                val builder = AlertDialog.Builder(it.context)
                builder.setTitle("Delete Animal")
                builder.setMessage("Are you sure you want to delete this Animal?")
                builder.setPositiveButton(android.R.string.yes) { function, which ->
                    //remove
                    GlobalVar.ListDataAnimal.removeAt(adapterPosition)
                notifyDataSetChanged()
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->

                }
                builder.show()


            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return data.size
    }
}