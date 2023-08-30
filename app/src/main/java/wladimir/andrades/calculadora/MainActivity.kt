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
    private var definedOperation: Boolean = false

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
    private fun validateButtonPressAfterEq(){
        if (this.validateTotal) {
            when (this.operation){
                "+", "-", "*", "/" -> {
                    setTextHistory("")
                    this.total = getTextValue().toInt()
                    this.validateTotal = false
                }
                else -> resetCalculator()
            }
        }
    }

    private fun validateTextOfTextValue(digit: String){
        validateButtonPressAfterEq()

        when(getTextValue()){
            "0", "Error" -> {
                setTextValue(digit)
                desOrActButtons(true)
            }
            else ->
                if (!this.definedOperation) setTextValue(digit)
                else setTextValue(getTextValue() + digit)
        }

        this.definedOperation = true
    }

    private fun validateButtonPress(operation: String){
        validateButtonPressAfterEq()

        if (getTextHistory().isNotBlank()){
            if (this.definedOperation) setTextHistory(getTextHistory() + getTextValue() + operation)
            else{
                when(getTextHistory().last()){
                    '0','1','2','3','4','5','6','7','8','9' -> setTextHistory(getTextHistory() + getTextValue() + operation)
                    '+', '-', '*', '/' -> setTextHistory(getTextHistory().dropLast(1) + operation)
                }
            }
        }
        else {
            setTextHistory(getTextValue() + operation)
            total = getTextValue().toInt()
        }

        setTextValue(total.toString())
        this.definedOperation = false
    }

    private fun validatePerformOperation() {
        if (operation.isNotBlank() && total != 0 && definedOperation) validateOperation()
    }

    private fun validateOperation(){
        when(this.operation){
            "+" -> this.total += getTextValue().toInt()
            "-" -> this.total -= getTextValue().toInt()
            "*" -> this.total *= getTextValue().toInt()
            "/" -> validateDivideByZero()
        }
    }

    private fun validateDivideByZero(){
        if (getTextValue() == "0"){
            setTextValue("Error")
            desOrActButtons(false)
            setTextHistory("")
            this.total = 0
            this.operation = ""
            this.validateTotal = false
        }
    }

    //Operations buttons
    fun pressSum(view: View){
        validatePerformOperation()

        this.operation = "+"
        validateButtonPress(this.operation)
    }

    fun pressRes(view: View){
        validatePerformOperation()

        this.operation = "-"
        validateButtonPress(this.operation)
    }

    fun pressMul(view: View) {
        validatePerformOperation()

        this.operation = "*"
        validateButtonPress(this.operation)
    }

    fun pressDiv(view: View){
        validatePerformOperation()

        this.operation = "/"
        validateButtonPress(this.operation)
    }

    fun pressEqual(view: View){
        setTextHistory(getTextHistory() + getTextValue() + "=")

        validateOperation()
        setTextValue(this.total.toString())
        this.validateTotal = true
        this.operation = ""
    }

    //Special buttons
    fun pressDel(view: View){
        validateButtonPressAfterEq()

        if (getTextValue().length == 1) setTextValue("0")
        else setTextValue(getTextValue().dropLast(1))
    }

    fun pressC(view: View){
        resetCalculator()
    }

    fun pressCE(view: View){
        validateButtonPressAfterEq()

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