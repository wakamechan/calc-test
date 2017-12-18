package example

import scala.swing._

object Calculator {
  def calculate(formula: String): String = {
    val tmpStack = new scala.collection.mutable.Stack[String]
    
    try {
      // 計算式を逆ポーランド記法にする
      val rpn = convertRPN(formula)
      println(rpn)
      
      // 計算開始
      while(!rpn.isEmpty) {
        val strTmp = rpn.pop()
        if(chkSymbol(strTmp)) {
          val topElem = tmpStack.pop().toDouble
          val secondElem = tmpStack.pop().toDouble
          
          strTmp match {
            case "+" => tmpStack.push((topElem + secondElem) toString)
            case "-" => tmpStack.push((topElem - secondElem) toString)
            case "×" => tmpStack.push((topElem * secondElem) toString)
            case "÷" => tmpStack.push((topElem / secondElem) toString)
            case _ => 
            }
        }
        else {
          tmpStack.push(strTmp)
        }
      }
      tmpStack.pop()
    } catch {
        case e: Exception => "エラー"
    }
  }
  
  def convertRPN(formula: String): scala.collection.mutable.Stack[String] = {
    val tmp: StringBuffer = new StringBuffer
    val st1 = new scala.collection.mutable.Stack[String]
    val st2 = new scala.collection.mutable.Stack[String]

    formula.foreach(
      c =>
        // 演算子が検出されるまで数字を退避
        if ((c >= '0' && c <= '9') || c.equals('.')) {
          tmp.append(c)
        }
        else if(c.equals('+') || c.equals('-') || c.equals('×') || c.equals('÷')) {
          if(tmp.length() != 0) {
            // 退避させた文字をスタックにpush
            st1.push(tmp.toString())
            tmp.setLength(0)
          }
          if(!st2.isEmpty) {
            if((c.equals('×') || c.equals('÷')) &&
                (st2.top.equals('+') || st2.top.equals('-'))) {
              st2.push(c.toString())
            }
            else {
              st1.push(st2.pop())
            }
          }
          else {
            st2.push(c.toString())
          }
        }
    )
    
    if (tmp.length() != 0) {
      st1.push(tmp.toString())
      tmp.setLength(0)
    }
    
    while(!st1.isEmpty) {
      st2.push(st1.pop())
    }
    
    st2
  }
  
  // 演算子かどうか判定
  def chkSymbol(str: String): Boolean = {
    if (str.equals("+") || str.equals("-") || str.equals("×") || str.equals("÷")) {
      true
    }
    else {
      false
    }
  }
}