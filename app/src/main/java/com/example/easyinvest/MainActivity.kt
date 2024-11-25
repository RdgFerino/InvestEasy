package com.example.easyinvest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.*
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var edtInserirValor: TextInputEditText
    private lateinit var edtQtdMeses: TextInputEditText
    private lateinit var edtJuros: TextInputEditText
    private lateinit var tvValRecebido: TextView
    private lateinit var tvValRendimento: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        edtInserirValor = findViewById(R.id.edt_inserir_val)
        edtQtdMeses = findViewById(R.id.edt_Qtd_men1)
        edtJuros = findViewById(R.id.edt_juros1)
        tvValRecebido = findViewById(R.id.tv_valrec2)
        tvValRendimento = findViewById(R.id.tv_valrend2)
        btnCalcular = findViewById(R.id.btn_calcular1)
        btnLimpar = findViewById(R.id.btn_limpar1)

        btnCalcular.setOnClickListener {
            calcularInvestimento()
        }

        btnLimpar.setOnClickListener {
            limparCampos()
        }


    }
    private fun calcularInvestimento() {
        // Obter valores de entrada
        val valorStr = edtInserirValor.text.toString()
        val mesesStr = edtQtdMeses.text.toString()
        val jurosStr = edtJuros.text.toString()

        // Verificar se os campos estão preenchidos
        if (valorStr.isEmpty() || mesesStr.isEmpty() || jurosStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }
        // Converter para valores numéricos
        val valor = valorStr.toDoubleOrNull()
        val meses = mesesStr.toIntOrNull()
        val juros = jurosStr.toDoubleOrNull()

        if (valor == null || meses == null || juros == null) {
            Toast.makeText(this, "Valores inválidos.", Toast.LENGTH_SHORT).show()
            return
        }

        // Calcular o valor total e os rendimentos
        val taxaMensal = juros / 100
        var totalInvestido = 0.0
        var valorFinal = 0.0

        for (i in 1..meses) {
            totalInvestido += valor
            valorFinal += valor * Math.pow(1 + taxaMensal, i.toDouble())
        }

        val rendimento = valorFinal - totalInvestido

        // Atualizar os TextViews com os resultados
        tvValRecebido.text = String.format("R$ %.2f", valorFinal)
        tvValRendimento.text = String.format("R$ %.2f", rendimento)
    }

    private fun limparCampos() {
        edtInserirValor.text?.clear()
        edtQtdMeses.text?.clear()
        edtJuros.text?.clear()
        tvValRecebido.text = "0.0"
        tvValRendimento.text = "0.0"
    }




    }



