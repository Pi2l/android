package m.m.androidlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
        val rolledText: TextView = findViewById(R.id.textView)
        rolledText.text = Dice().roll().toString()
    }
}

