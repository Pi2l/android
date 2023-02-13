package m.m.androidlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollerButton: Button = findViewById(R.id.button)
        rollerButton.setOnClickListener { rollDice() }
    }

    private fun rollDice() {
        val rolledImage: ImageView = findViewById(R.id.imageView)
        rolledImage.setImageResource(R.drawable.dice_1)

        var rolledDice: Int = Dice().roll()
        var imageToDraw: Int = when (rolledDice) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> { R.drawable.dice_6 }
        }
        rolledImage.contentDescription = rolledDice.toString()
        rolledImage.setImageResource(imageToDraw)

    }
}

