import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.*;

public class PublicationGUI implements ActionListener{

    private final JTextField mId;
    private final JTextField mName;
    private final JTextField mAuthor;
    private final JTextField mPrice;

    private final JButton add;
    private final JButton search;
    private final JButton clear;
    private final JButton reset;

    private final JComboBox<String> publishTeam;

    private ArrayList<Book> books;

    public PublicationGUI() {

        books = new ArrayList<>();
        JFrame frame = new JFrame();
        frame.setTitle("Book Publication Menu");

        JLabel nameL = new JLabel("Book Name:");
        JLabel idL = new JLabel("ID:");
        JLabel authorL = new JLabel("Author Name:");
        JLabel priceL = new JLabel("Price:");
        JLabel pubTm = new JLabel("Publisher Name:");

        mName = new JTextField();
        mId = new JTextField();
        mAuthor = new JTextField();
        mPrice = new JTextField();

        String [] options = {"Penguin", "HarperCollins", "Macmillan", "Pearson"};
        publishTeam = new JComboBox<>(options);

        add = new JButton("Publish");
        search = new JButton("Search(by name)");
        reset = new JButton("Reset Menu");
        clear = new JButton ("Clear");

        add.addActionListener(this);
        search.addActionListener(this);
        reset.addActionListener(this);
        clear.addActionListener(this);

        GridLayout g = new GridLayout(7, 1);
        frame.setLayout(g);

        frame.add(nameL);
        frame.add(mName);
        frame.add(idL);
        frame.add(mId);
        frame.add(authorL);
        frame.add(mAuthor);
        frame.add(priceL);
        frame.add(mPrice);
        frame.add(pubTm);
        frame.add(publishTeam);
        frame.add(add);
        frame.add(search);
        frame.add(reset);
        frame.add(clear);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 580);
        frame.setLocation(200, 100);

        readFromFile();
    }

    public void readFromFile(){
        try {
            File f = new File("records.txt");

            Scanner scanner = new Scanner(f);
            books = new ArrayList<>();

            while(scanner.hasNextLine()){
                String name = scanner.nextLine();
                String id = scanner.nextLine();
                String author = scanner.nextLine();
                String price = scanner.nextLine();
                String publisher = scanner.nextLine();

                Book temp = new Book(name,id, author,price,publisher);
                books.add(temp);
            }

            scanner.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(Book book){
        try {
            File f = new File("records.txt");
            FileWriter fileWriter = new FileWriter(f);

            if (book != null) {
                fileWriter.write(" Name:" + book.getName());
                fileWriter.write(" ID:" + book.getId());
                fileWriter.write(" Author:" + book.getAuthor());
                fileWriter.write(" Price:" + book.getPrice());
                fileWriter.write(" Publisher:" + book.getPublishTeam());

                fileWriter.write("\n");
            }

            fileWriter.close();
            JOptionPane.showMessageDialog(null,"SAVED!");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addBook(Book book){
        books.add(book);
    }

    public Book findPublish(String key){
        Book temp = null;
        for (Book book : books) {
            if (book != null && (book.getName().equals(key) || book.getId().equals(key))) { // name matches
                temp = book;
            }
        }

        return temp;
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == add){

            String name = mName.getText();
            String id = mId.getText();
            String author = mAuthor.getText();
            String price = mPrice.getText();
            String publisher = Objects.requireNonNull(publishTeam.getSelectedItem()).toString();

            if(!name.equals("") && !id.equals("") && !author.equals("") && !price.equals("") && !publisher.equals("")) {
                Book book = new Book(name, id, author, price, publisher);
                addBook(book);
                writeToFile(book);
            }

        }

        if(e.getSource() == search){
            String name = mName.getText();
            String id = mId.getText();

            Book found = findPublish(name);
            Book book = findPublish(id);

            if(found != null ){
                mName.setText(found.getName());
                mId.setText(found.getId());
                mAuthor.setText(found.getAuthor());
                mPrice.setText(found.getPrice());

            } else if (book != null){
                mName.setText(book.getName());
                mId.setText(book.getId());
                mAuthor.setText(book.getAuthor());
                mPrice.setText(book.getPrice());

            } else{
                JOptionPane.showMessageDialog(null,"Sorry,No record found!");
            }
        }

        if (e.getSource()== reset){
            mName.setText("");
            mId.setText("");
            mAuthor.setText("");
            mPrice.setText("");

            JOptionPane.showMessageDialog(null,"Reset");
        }

        if(e.getSource() == clear){
            try {
                File f = new File("records.txt");
                FileWriter fw = new FileWriter(f);

                String a = mName.getText();
                String b = mId.getText();

                Book found = findPublish(a);
                Book publishedBook = findPublish(b);

                if(found != null){
                    for (Book book : books) {
                        if (book != null)
                            fw.write("\n");

                    }
                }
                if(publishedBook != null){
                    for (Book book : books) {
                        if (book != null) {
                            fw.write("\n");
                        }
                    }
                }

                fw.close();
                JOptionPane.showMessageDialog(null,"records deleted!");

            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
	