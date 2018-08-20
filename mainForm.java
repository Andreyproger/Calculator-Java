import calculator.AbstractValueParser;
import calculator.Calculator;
import datatypes.complex.ComplexValueParser;
import datatypes.integer.IntegerValueParser;
import datatypes.rational.RationalValueParser;
import datatypes.real.RealValueParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

/**
 * Created by Andre on 11.03.2017.
 */
public class mainForm {
    private static AbstractValueParser[] valueParsers = new AbstractValueParser[] {
            new IntegerValueParser(), //разделитель для целых
            new RealValueParser(),
            new ComplexValueParser(),
            new RationalValueParser()
    };

    private static JFrame frame;    //Окно
    private static JFrame help_frame;     //Окно для справки
    private static JTextArea txtFirstVar = new JTextArea();     //Тектовое поле для первого аргумента
    private static JTextArea txtSecondVar = new JTextArea();    //Текстовое поле для 2ого аргумента
    private static JTextArea txtRes = new JTextArea();          //Текстовое поле для результата
    private static JComboBox cboParsers;                        //Выпадающий список типов
    private static JComboBox cboOperators;                      //Выпадающий список знаков действий (+, -...


    //Главная функция (точка входа)
    public static void main(String[] args)
    {
        makeGUI();  //Вызов функции отрисовывающей пользовательский интерфейс
    }

    //функция отрисовывающая пользовательский интерфейс
    public static void makeGUI()
    {
        frame=new JFrame("Мой первый калькулятор");  //Окно приложения
        frame.setSize(500, 200);    //Размер окна
        frame.setLayout(new GridLayout(6,2,2,2));   // сетка (таблица) из 6 строк и 2 столбцов с разделением по 2 на вертикаль и 2 на горизонтали

        JPanel panels[] = new JPanel[14];//JPanel panels[] = new JPanel[12];   //Массив панелей (ими заполняем ячейки таблицы). Панели - контейнеры объектов
        for (int i=0;i<12;i++) {    //Их всего 12
            panels[i] = new JPanel();   //Вызываем конструктор для создания панели
            panels[i].setLayout(new BoxLayout(panels[i],BoxLayout.Y_AXIS));     //менеджер определяет расположение элементов
            frame.add(panels[i]);   //Добавляем панель на форму (она попадет в очередную ячейку)
        }
        String opeartors[] = {"+", "-", "*", "/"};  //Список операторов

        cboParsers   = new JComboBox(getParsers(valueParsers));     //Создание выпадающего списка парсеров
        cboOperators = new JComboBox(opeartors);                    //Создание выпадающего списка операторов
        JButton btnRes = new JButton("Результат");                //Создание кнопки


        //На i-ую панель добавляем соотвествующий элемент
        panels[0].add(cboParsers);
        panels[2].add(new JLabel("Первое число:"));
        panels[3].add(txtFirstVar);
        panels[5].add(cboOperators);
        panels[6].add(new JLabel("Второе число:"));
        panels[7].add(txtSecondVar);
        panels[8].add(new JLabel("В итоге:"));
        panels[9].add(txtRes);
        panels[10].add(btnRes);
        btnRes.addActionListener(new OnClick());    //Назначаем обработчика нажатия клавишип

        //Создаём меню
        JMenuBar mainMenu = new JMenuBar(); //Панель меню
        JMenu calcMenu = new JMenu("Калькулятор");  //Главное меню
        JMenuItem saveRes = new JMenuItem("Сохранить результат");   //Подменю
        JMenuItem help = new JMenuItem("Справка"); // Подменю
        saveRes.addActionListener(new save_OnClick());  //Назначем класс, который отвечаем за действия при нажатии на пункт меню
        calcMenu.add(saveRes);
        mainMenu.add(calcMenu);
        frame.setJMenuBar(mainMenu);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //Говорим, что выхода из программы не будет пока не закрыто окно
        frame.setVisible(true);     //Делаем форму видимой

        /*help_frame=new JFrame("Мой первый калькулятор");  //Окно приложения
        help_frame.setSize(300, 200);
        help_frame.setVisible(true);*/

    }

    /*public static void makeFrame(){
        help_frame=new JFrame("Справка");
        help_frame.setSize(300,250);
        help_frame.setLayout(new GridLayout(2,1));
    }*/

    //По списку объектов-парсеров возвращает список строк
    private static String[] getParsers(AbstractValueParser[] avp)
    {
        String res[] = new String[avp.length];
        for (int i = 0; i<avp.length; i++)
            res[i] = avp[i].getDatatypeName();
        return res;
    }

    //Класс обработки нажатия кнопки Результат
    private static class OnClick implements ActionListener
    {
        //функция, которая выполняется при нажатии кнопки результат
        public void actionPerformed(ActionEvent e) {
            AbstractValueParser currentParser = valueParsers[cboParsers.getSelectedIndex()];    //Берём парсер (смотрим, какая строка по счёту выделена в выпадающем списке и берём парсер из массива valueParsers по этому номеру
            String arg1 = txtFirstVar.getText();    //Первый аргумент (берём текст из соответсвующего текстового поля)
            String arg2 = txtSecondVar.getText();   //Воторой аргумент (берём текст из соответсвующего текстового поля)
            String op = cboOperators.getSelectedItem().toString();  //Оператор
            Calculator calc = new Calculator(currentParser);    //Создаём объект калькулятор
            try
            {
                txtRes.setText(calc.calculate(arg1, op, arg2)); //Пытаемся посчитать и вывести результат в соответствующее текстовое поле
            }
            catch (Exception ex)
            {
                txtRes.setText(ex.getMessage());    //В случае ошибки выводим её сообщение в текстовое поле
            }
        }
    }

    private static class save_OnClick implements ActionListener
    {

        //При нажатии пункта меню сохранить
        public void actionPerformed(ActionEvent e) {
                String op = cboOperators.getSelectedItem().toString();
                JFileChooser fc = new JFileChooser();   //Диалог выбора файла
                if (fc.showDialog(frame, "Сохранить") == JFileChooser.APPROVE_OPTION)   //Открываем диалог
                    //Если файл выбрали пытаемся в него записать
                    try
                    {
                        FileWriter fw = new FileWriter(fc.getSelectedFile());   //fw работает с файлом (пишет в него), fc.getSelectedFile() - возвращает выбранный файл
                        fw.write(txtFirstVar.getText());
                        fw.write(" " + op + " ");
                        fw.write(txtSecondVar.getText());
                        fw.write(" = " + txtRes.getText());     //пишем в файл текст, который находится в текстовом поле результата

                        fw.flush();     //Если в буфере что-то осталось, переносим в файл
                        fw.close();     //закрываем файл
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());  //Если ошибка, то выдаём сообщение
                    }
        }
    }

}