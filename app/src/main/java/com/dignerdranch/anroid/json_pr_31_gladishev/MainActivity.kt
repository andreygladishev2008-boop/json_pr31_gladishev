package com.dignerdranch.anroid.json_pr_31_gladishev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private var generatedJsonString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etProductName = findViewById<EditText>(R.id.etProductName)
        val etProductPrice = findViewById<EditText>(R.id.etProductPrice)
        val etProductTags = findViewById<EditText>(R.id.etProductTags)

        val btnSerialize = findViewById<Button>(R.id.btnSerialize)
        val btnDeserialize = findViewById<Button>(R.id.btnDeserialize)

        val tvJsonResult = findViewById<TextView>(R.id.tvJsonResult)
        val tvObjectResult = findViewById<TextView>(R.id.tvObjectResult)

        etProductName.setText("Программирование на Kotlin")
        etProductPrice.setText("1500.0")
        etProductTags.setText("учебник, программирование, android")

        btnSerialize.setOnClickListener {
            val product = Product()
            product.name = etProductName.text.toString()
            product.price = etProductPrice.text.toString()
            product.tags = etProductTags.text.toString()

            generatedJsonString = Gson().toJson(product)
            tvJsonResult.text = generatedJsonString
        }

        btnDeserialize.setOnClickListener {
            try {
                val restoredProduct = Gson().fromJson(generatedJsonString, Class.forName("com.dignerdranch.anroid.json_pr_31_gladishev.Product")) as Product


                val resultText = "Название: " + restoredProduct.name +
                        "\nЦена: " + restoredProduct.price + " руб." +
                        "\nТеги: " + restoredProduct.tags

                tvObjectResult.text = resultText
            } catch (e: Exception) {
                tvObjectResult.text = "Ошибка"
            }
        }
    }
}