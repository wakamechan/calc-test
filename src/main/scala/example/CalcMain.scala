package example

import scala.swing._
import scala.swing.event.ButtonClicked
import java.awt.Color

object CalcMain extends SimpleSwingApplication {
  // フレーム作成
  def top = new MainFrame {
    title = "電卓"
    minimumSize = new Dimension(400, 500)
    contents = gridBagPanel
  }
  
  // フレームの中身
  val gridBagPanel = new GridBagPanel() {
    // ボタンのテキストと表示位置
    val buttonMap = Map (
      0 -> ("0", pair2Constraints(0, 5)),
      1 -> ("1", pair2Constraints(0, 4)),
      2 -> ("2", pair2Constraints(1, 4)),
      3 -> ("3", pair2Constraints(2, 4)),
      4 -> ("4", pair2Constraints(0, 3)),
      5 -> ("5", pair2Constraints(1, 3)),
      6 -> ("6", pair2Constraints(2, 3)),
      7 -> ("7", pair2Constraints(0, 2)),
      8 -> ("8", pair2Constraints(1, 2)),
      9 -> ("9", pair2Constraints(2, 2)),
      10 -> ("+", pair2Constraints(3, 5)),
      11 -> ("-", pair2Constraints(3, 4)),
      12 -> ("×", pair2Constraints(3, 3)),
      13 -> ("÷", pair2Constraints(3, 2)),
      14 -> ("AC", pair2Constraints(3, 1)),
      15 -> ("(", pair2Constraints(0, 1)),
      16 -> (")", pair2Constraints(1, 1)),
      17 -> ("%", pair2Constraints(2, 1)),
      18 -> (".", pair2Constraints(1, 5)),
      19 -> ("=", pair2Constraints(2, 5))
    )
    
    // ボタンの設定
    val buttonArray = new Array[Button](buttonMap.size)
    for (buttonNum <- 0 until buttonMap.size) {
      // ボタンの生成
      buttonArray(buttonNum) = new Button(buttonMap(buttonNum)._1) {
        preferredSize = new Dimension(100, 50)
        // ボタン押下時の動作を登録
        reactions += {
          case e: ButtonClicked => {
            e.source.text match {
              case "=" => {
                val result = Calculator.calculate(textArea.text)
                textArea.text_=(result)
              }
              case "AC" => textArea.text_=("")
              case _ => textArea.text_=(textArea.text + e.source.text)
            }
          }
        }
      }
      // ボタンの配置を設定
      layout += buttonArray(buttonNum) -> buttonMap(buttonNum)._2
    }
    
    // 計算結果を表示する部分を生成
    val a = pair2Constraints(0, 0)
    a.gridwidth = 4
    val textArea = new TextArea {
      preferredSize = new Dimension(400, 50)
      background = Color.white
    }
    textArea.editable_=(false)
    layout += textArea -> a
  }
}
