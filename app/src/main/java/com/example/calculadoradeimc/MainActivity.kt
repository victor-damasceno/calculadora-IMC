package com.example.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.calculadoradeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //No processo acima foi utilizado o viewBinding, com a variável binding, para poder recuperar os IDs dos componentes.

        val mensagemID = binding.txtMensagem
        val bt_calcular = binding.btCalcular

        //Foi implementada uma ação no botão calcular assim que ele for clicado.

        bt_calcular.setOnClickListener {

            val pesoID = binding.peso
            val alturaID = binding.altura

            //Caso o usuário não coloque o peso e nem a altura, ou apenas um dos dois, aparecerá um texto informando o campo específico que está vazio
            //e precisa ser preenchido.

            when {

                pesoID.text.isEmpty() && alturaID.text.isEmpty()-> mensagemID.setText("Informe o seu peso e a sua altura.")
                pesoID.text.isEmpty() -> mensagemID.setText("Informe o seu peso.")
                alturaID.text.isEmpty() -> mensagemID.setText("Informe a sua altura")
                else -> {
                    resultadoIMC()
                }
            }
        }
    }

    //Caso o usuário coloque todos os dados necessários, a função abaixo vai mostrar seu IMC e a mensagem específica determinado resultado.

    fun resultadoIMC() {

        //Como a entrada de dados vem, por padrão, como String, abaixo criei variáveis em que, dentro delas, vai converter o tipo String para Float.

        val peso = java.lang.Float.parseFloat(binding.peso.text.toString())
        val altura = java.lang.Float.parseFloat(binding.altura.text.toString())
        val mensagem = binding.txtMensagem

        //Aqui, na parte do processamento, é o cálculo do IMC, onde a altura vezes ela própria será dividida pelo peso, encontrando assim
        //o resultado. Logo em seguida, como saída de dados, irá apresentar a mensagem final ao usuário.

        val imc = peso / (altura * altura)
        val mensagemFinal = when {

            imc <18.5 -> "Peso Baixo."
            imc <=24.9 -> "Peso Normal."
            imc <=29.9 -> "Sobrepeso."
            imc <=34.9 -> "Obesidade (Grau 1)."
            imc <=39.9 -> "Obesidade Severa (Grau 2)."
            else -> "Obesidade Mórbida (Grau 3)."

        }

        imc.toString()
        mensagem.setText("IMC: $imc \n $mensagemFinal")
    }

    //Nessa parte é onde vai mostrar o botão refresh no canto superior direito, que já é padrão do sistema operacional.

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    //Assim que esse botão refresh for clicado, os campos de peso e altura serão esvaziados assim como a mensagem final.

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset ->{

                val limparPeso = binding.peso
                val limparAltura = binding.altura
                val limparMensagem = binding.txtMensagem

                limparPeso.setText("")
                limparAltura.setText("")
                limparMensagem.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}