package wladimir.andrades.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var total: Int = 0
    private var operation: String = ""
    private var validateTotal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    //Get and edit properties
    private fun setTextHistory(text: String){
        val txtHistory: TextView = findViewById(R.id.txtHistory)
        txtHistory.text = text
    }

    private fun setTextValue(text: String){
        val txtValue: TextView = findViewById(R.id.txtValue)
        txtValue.text = text
    }

    private fun getTextHistory(): String{
        val txtHistory: TextView = findViewById(R.id.txtHistory)
        return txtHistory.text.toString()
    }

    private fun getTextValue(): String{
        val txtValue: TextView = findViewById(R.id.txtValue)
        return txtValue.text.toString()
    }

    private fun desOrActButtons(action: Boolean){
        val btnCE: Button = findViewById(R.id.btnCE)
        val btnC: Button = findViewById(R.id.btnC)
        val btnDel: Button = findViewById(R.id.btnDel)
        val btnEq: Button = findViewById(R.id.btnEq)
        val btnSum: Button = findViewById(R.id.btnSum)
        val btnRes: Button = findViewById(R.id.btnRes)
        val btnMul: Button = findViewById(R.id.btnMul)
        val btnDiv: Button = findViewById(R.id.btnDiv)

        btnCE.isEnabled = action
        btnDel.isEnabled = action
        btnEq.isEnabled = action
        btnSum.isEnabled = action
        btnRes.isEnabled = action
        btnMul.isEnabled = action
        btnDiv.isEnabled = action
    }

    private fun resetCalculator(){
        this.total = 0
        this.operation = ""
        this.validateTotal = false

        setTextHistory("")
        setTextValue("0")
    }

    //Validation operation
    private fun validateTextOfTextValue(digit: String){
        when(getTextValue()){
            "0", "Error"-> {
                setTextValue(digit)
                desOrActButtons(true)
            }
            else -> if (this.validateTotal) setTextValue(digit)
            else setTextValue(getTextValue() + digit)
        }
        this.validateTotal = false
    }

    private fun validateOperation(){
        when(this.operation){
            "+" -> this.total += getTextValue().toInt()
            "-" -> this.total -= getTextValue().toInt()
            "*" -> this.total *= getTextValue().toInt()
            "/" -> this.total /= getTextValue().toInt()
        }
    }

    //Operations buttons
    fun pressSum(view: View){
        this.operation = "+"
        if (getTextHistory().isNotBlank()){
            when(getTextHistory().last()){
                '0','1','2','3','4','5','6','7','8','9' -> setTextHistory(getTextHistory() + getTextValue() + operation)
                '+', '-', '*', '/' -> setTextHistory(getTextHistory().dropLast(1) + operation)
            }
        }
        else{
            setTextHistory(getTextValue() + operation)
        }
    }

    fun pressRes(view: View){
        this.operation = "-"
        if (getTextHistory().isNotBlank()){
            when(getTextHistory().last()){
                '0','1','2','3','4','5','6','7','8','9' -> setTextHistory(getTextHistory() + getTextValue() + operation)
                '+', '-', '*', '/' -> setTextHistory(getTextHistory().dropLast(1) + operation)
            }
        }
        else{
            setTextHistory(getTextValue() + operation)
        }
    }

    fun pressMul(view: View){
        this.operation = "*"
        if (getTextHistory().isNotBlank()){
            when(getTextHistory().last()){
                '0','1','2','3','4','5','6','7','8','9' -> {
                    setTextHistory(getTextHistory() + getTextValue() + operation)

                }
                '+', '-', '*', '/' -> setTextHistory(getTextHistory().dropLast(1) + operation)
            }
        }
        else{
            setTextHistory(getTextValue() + operation)
        }
    }

    fun pressDiv(view: View){
        this.operation = "/"
        if (getTextHistory().isNotBlank()){
            when(getTextHistory().last()){
                '0','1','2','3','4','5','6','7','8','9' -> setTextHistory(getTextHistory() + getTextValue() + operation)
                '+', '-', '*', '/' -> setTextHistory(getTextHistory().dropLast(1) + operation)
            }
        }
        else{
            setTextHistory(getTextValue() + operation)
        }
    }

    fun pressEqual(view: View){
        setTextHistory(getTextHistory() + getTextValue() + "=")

        when (operation) {
            "/" -> {
                //validateDivideByZero("")
                setTextValue(this.total.toString())
                this.validateTotal = true
                desOrActButtons(false)
            }
            else -> {
                validateOperation()
                setTextValue(this.total.toString())
                this.validateTotal = true
                desOrActButtons(false)
            }
        }
    }

    //Special buttons
    fun pressDel(view: View){
        if (getTextValue().length == 1) setTextValue("0")
        else setTextValue(getTextValue().dropLast(1))
    }

    fun pressC(view: View){
        resetCalculator()
    }

    fun pressCE(view: View){
        setTextValue("0")
    }

    //Press digit
    fun pressDigit1(view: View){
        validateTextOfTextValue("1")
    }

    fun pressDigit2(view: View){
        validateTextOfTextValue("2")
    }

    fun pressDigit3(view: View){
        validateTextOfTextValue("3")
    }

    fun pressDigit4(view: View){
        validateTextOfTextValue("4")
    }

    fun pressDigit5(view: View){
        validateTextOfTextValue("5")
    }

    fun pressDigit6(view: View){
        validateTextOfTextValue("6")
    }

    fun pressDigit7(view: View){
        validateTextOfTextValue("7")
    }

    fun pressDigit8(view: View){
        validateTextOfTextValue("8")
    }

    fun pressDigit9(view: View){
        validateTextOfTextValue("9")
    }

    fun pressDigit0(view: View){
        validateTextOfTextValue("0")
    }
}