
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.imageio.ImageIO;
import static nonuniformquantizer.NonUniformQuantizer.readImage;
import static nonuniformquantizer.NonUniformQuantizer.writeImage;

public class Quantizer extends javax.swing.JFrame {

    public Quantizer() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Enter number of levels :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Compress");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Decompress");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(170, 170, 170))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     public static int[][] readImage(String filePath)
    {
	    int width=0;
		int height=0;
        File file=new File(filePath);
        BufferedImage image=null;
        try
        {
            image=ImageIO.read(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

          width=image.getWidth();
          height=image.getHeight();
        int[][] pixels=new int[height][width];

        for(int x=0;x<width;x++)
        {
            for(int y=0;y<height;y++)
            {
                int rgb=image.getRGB(x, y);
                int alpha=(rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb >> 0) & 0xff;

                pixels[y][x]=r;
            }
        }

        return pixels;
    }
    
    public static void writeImage(int[][] pixels,String outputFilePath,int width,int height)
    {
        File fileout=new File(outputFilePath);
        BufferedImage image2=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB );

        for(int x=0;x<width ;x++)
        {
            for(int y=0;y<height;y++)
            {
                image2.setRGB(x,y,(pixels[y][x]<<16)|(pixels[y][x]<<8)|(pixels[y][x]));
            }
        }
        try
        {
            ImageIO.write(image2, "jpg", fileout);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    int indxOF_min_distance (ArrayList <Double> distance_difference )
    {
        double min_diff = distance_difference.get(0); // assume first element is the min 
        int indx = 0 ;
        
        for (int i=1 ; i<distance_difference.size() ; i++)
        {
            if ( distance_difference.get(i) < min_diff)
            {
               min_diff = distance_difference.get(i);
               indx = i ;
            }
            
        }
        
        return indx ;
    }
     
    class split_element 
    {
        int value ;
        ArrayList<Integer> assoicated = new ArrayList<>();

        public split_element(int value , ArrayList<Integer> associated) {
            this.value = value;
            this.assoicated = associated;
        }
         public split_element(){
        }

        public void setValue(int value ) {
            this.value = value;
        }

        public void setAssoicated(ArrayList<Integer> assoicated) {
            this.assoicated = assoicated;
        }
        
        public int getValue() {
            return value;
        }

        public ArrayList<Integer> getAssoicated() {
            return assoicated;
        }
        
        
    }
    
    class Row // level OR Range
    {
        int start ;
        int end ;
        int Q ;
        int Q_1 ;

        public Row(int start, int end, int Q, int Q_1) {
            this.start = start;
            this.end = end;
            this.Q = Q;
            this.Q_1 = Q_1;
        }

        public Row() { }
        
        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getQ() {
            return Q;
        }

        public void setQ(int Q) {
            this.Q = Q;
        }

        public int getQ_1() {
            return Q_1;
        }

        public void setQ_1(int Q_1) {
            this.Q_1 = Q_1;
        }
        
        
    }
    
    ArrayList<Integer>  associate ( ArrayList <Integer> split , ArrayList <Integer> data  ) // associate ang return avg
    {   
        
        ArrayList <split_element> Split = new ArrayList<>();
        ArrayList <Integer> Averages = new ArrayList<> ();
        
        for (int i = 0; i < split.size(); i++)  // inilialization 
        {  
           split_element initial = new split_element() ;
           initial.setValue(split.get(i));
           Split.add(initial);
        }
        
        for (int i=0 ; i<data.size() ; i++) // associate data
          {
                  int cur = data.get(i);
                  ArrayList <Double> distance_difference = new ArrayList<> ();
                  
                  for (int j=0 ; j<split.size() ;j++)
                  {   
                      int value = cur - split.get(j);
                      double distanc_diff =  Math.pow( value , 2);
                      distance_difference.add(distanc_diff);
                  }
                  
                  int indx = indxOF_min_distance (distance_difference);
                  
                  ArrayList <Integer> cur_associated = Split.get(indx).getAssoicated();
                  
                  cur_associated.add(cur);
                 
                  split_element New = new split_element(Split.get(indx).getValue() , cur_associated);
                  
                  Split.set(indx , New );
                  
          }
        
        for (int i=0 ; i<Split.size() ; i++) // calculate average for the associated values
        {
            int total = 0 ;
            int arraysize = Split.get(i).getAssoicated().size();
            
            for (int j=0 ; j<arraysize ; j++)
            {
                total+= Split.get(i).getAssoicated().get(j);
            }
            
            if (total !=0 )
            {
                int avg = total / arraysize;
                Averages.add(avg);
            }
        }
        
        return Averages ;
    }
    
    ArrayList<Integer> Split (ArrayList <Integer> Averages ,  ArrayList <Integer> data , int numoflevels ) // split original averages
    {
         for (int i=0 ; i<Averages.size() ; i++)
        {   
            if (Averages.size()<numoflevels)
            {
              
            ArrayList <Integer> split = new ArrayList<>();
            
            for (int j=0 ; j<Averages.size() ; j++)
            {
              split.add(Averages.get(j)-1);
              split.add(Averages.get(j)+1);  
            }
            
            Averages.clear();
           
            Averages = associate( split , data);
            
            i=0 ;
            
            }
            
            else 
                break;
            
        } 
         
         return Averages ;
    }
    
     ArrayList<Integer> modify ( ArrayList<Integer> prev_Averages , ArrayList<Integer> new_Averages , ArrayList<Integer> data  )
    {
       while (true)
        {
           // calc avg diff 
           int totaldiff = 0 ; 
           double avgdiff = 0 ;
           
           for (int i=0 ; i<prev_Averages.size() ; i++)
           {      
               int diff = 0 ;
           
                   if (prev_Averages.get(i) >= new_Averages.get(i))
                   {
                       diff = prev_Averages.get(i)- new_Averages.get(i) ;
                   }
                   else
                   {
                      diff =  new_Averages.get(i) - prev_Averages.get(i) ; 
                   }
                   
               totaldiff+=diff ;
               
           }
            
           avgdiff = totaldiff / prev_Averages.size() ;
           
           if (avgdiff < 0.0001)
           {
               break;
           }
           
           else 
           {
               prev_Averages = new_Averages ;
               new_Averages = associate( new_Averages , data);
           }
           
        }
       
       return new_Averages ;
        
    }
    
    void construct_Quantizer ( ArrayList<Row>table , ArrayList<Integer> new_Averages)
    {
        int start = 0 ;
        
        for (int i=0 ; i<new_Averages.size() ; i++)
        {   
            int end ;
            
            if (i + 1 < new_Averages.size()) 
            {
                 end = ((new_Averages.get(i) + new_Averages.get(i + 1)) / 2 )-1 ;
            } 
            else
            {
                 end = 255 ; 
            }
            
            int Q_1 = new_Averages.get(i)  ;
            Row R = new Row (start , end , i , Q_1);
            table.add(R);
            start = end+1 ;
           
        }
        
    }
    
    void Quantization ( int numoflevels , int matrix [][]  )
    {    
         ArrayList <Integer> data = new ArrayList<>();
         ArrayList <Integer> Averages = new ArrayList<>(); 
         int height = matrix.length;
         int width = matrix[0].length;
         
        for (int i = 0; i < height; i++) 
        {
            for (int j = 0; j < width; j++)
            {
                data.add(matrix[i][j]);
            }
        }
        
        // initalize first avg
        int total = 0, avg = 0;

        for (int i = 0; i < data.size(); i++) {
            total += data.get(i);
        }

        avg = total / data.size() + 1;
        Averages.add(avg);
         
        
        Averages = Split (Averages , data , numoflevels);
        System.out.println("Done split ");
       
        // el ta7seen 
        ArrayList<Integer> prev_Averages = Averages ;
        ArrayList<Integer> new_Averages = associate( Averages , data); 
        
        new_Averages = modify(prev_Averages, new_Averages, data);
       
        ArrayList <Row> table = new ArrayList<>();
        construct_Quantizer (table , new_Averages);
        
        
        System.out.println("---------------- final Quantizer -----------------------");
        for (int i=0 ; i<table.size() ; i++)
        {
            System.out.println( "[ " + table.get(i).getStart() + "..." +  table.get(i).getEnd() + " ] " + " Q: " + table.get(i).getQ() + " Q-1: " + table.get(i).getQ_1());
        }
        
        compress (table , matrix );
 
    } 
    
    void compress ( ArrayList <Row> table , int [][] matrix )
    {   
        int rows = matrix.length;
        int cols = matrix[0].length ;
        int compress_matrix [][] = new int [rows][cols] ;
        
       for (int i=0 ; i<rows ; i++)
        {
            for (int j=0 ; j<cols ; j++)
            {
                int val = matrix[i][j];
                for (int k=0 ; k<table.size() ; k++)
                {
                    if ( val >= table.get(k).getStart() &&  val <=table.get(k).getEnd())
                    {
                        compress_matrix[i][j]= table.get(k).Q ;
                        break;
                    }
                }
            }
        }
       
        Save ( table , compress_matrix , "Compress.txt");
        
    }
     
    Scanner sc;

    public void open_file(String FileName) {
        try {
            sc = new Scanner(new File(FileName));
        } catch (Exception e) {
            jLabel2.setText("Can not find File ");
        }
    }

    public void close_file() {
        sc.close();
    }

    Formatter out; //34an yktb el tag be format el string

    public void openfile(String pass) {
        try {
            out = new Formatter(pass);
        } catch (Exception e) {
            jLabel2.setText("Can not find File ");
        }

    }

    public void closefile() {
        out.close();
    }
    
    void write(String code) {

        out.format("%s", code);
        out.format("%n");
        out.flush(); // 34an yktb 3al file

    }
     
    void Save (ArrayList <Row> table , int [][] matrix , String path )
    {
        openfile(path);
        
        String table_size = "" + table.size();
        write(table_size);
        
        for (int i=0 ; i<table.size() ; i++)
        {
            String level =  table.get(i).getStart() + " " + table.get(i).getEnd() + " " + table.get(i).getQ() + " " + table.get(i).getQ_1();
            write(level);
        }
        
        
        int rows = matrix.length;
        int cols = matrix[0].length ;
        
        String matrix_dimension = ""+rows+" "+cols;
       
        write(matrix_dimension);
        
        for (int i=0 ; i<rows ; i++)
        {  
            String Row = "";
            
            for (int j=0 ; j<cols ; j++)
            {
                int val = matrix[i][j];
                Row += val + " ";
                
            }
            
            write(Row);
        }
        
        closefile();
        
    }
    
    void Read_Quantizer (ArrayList <Row> table)
    {
          
        int table_size = Integer.parseInt(sc.nextLine());
        for (int i=0 ; i<table_size ; i++)
        {
            String level = sc.nextLine();
            String [] arr = level.split(" ");
            Integer.parseInt(arr[0]);
            Row cur_row = new Row (  Integer.parseInt(arr[0]) ,  Integer.parseInt(arr[1]) ,  Integer.parseInt(arr[2]) ,  Integer.parseInt(arr[3]));
            table.add(cur_row);
        }
        
        
        
    }
    
    int [][] Read_Image () // read comp mn l file
    {
        String matrix_dimension = sc.nextLine() ;
        String [] dimensions = matrix_dimension.split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        int [][] matrix = new int [rows][cols];
        
        for (int i=0 ; i<rows ; i++)
        {  
            String Row = sc.nextLine();
            String [] elements = Row.split(" ");
            
            for (int j=0 ; j<cols ; j++)
            {
                matrix[i][j] = Integer.parseInt(elements[j]);
            }
            
        }
        
        return matrix ;
    }
    
    void Decompress ()
    {   
        open_file("Compress.txt");
        
        ArrayList<Row> table = new ArrayList<>();
        Read_Quantizer(table);
        int [][] img = Read_Image() ;
        
        close_file();
   
        System.out.println("Done Reading comp file ");
        
        int [][] decomp_img = new int [img.length][img[0].length] ;
        
        for (int i=0 ; i<img.length ; i++)
        {
            for (int j=0 ; j<img[0].length ; j++)
            {
                int Q = img[i][j];
                decomp_img[i][j] = table.get(Q).getQ_1();
                
            }
        }
        
        System.out.println("Done decomp ");
        
        writeImage(decomp_img, "Decompressed Image.jpg", img[0].length , img.length); // width = img.length  &  height = img[0].length
        
        System.out.println("Done Write ");
    }
    
    void original_img (int img[][])
    {
        openfile("Original file.txt");
        int rows = img.length ;
        int cols = img[0].length ;
        
        for (int i=0 ; i<rows ; i++)
        {  
            String Row = "";
            
            for (int j=0 ; j<cols ; j++)
            {
                int val = img[i][j];
                Row += val + " ";
                
            }
            
            write(Row);
        }
        
        closefile();
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            
          int numOFLevels = Integer.parseInt(jTextField1.getText());
          int img[][] = readImage("lena.jpg");  
         // original_img (img);
          Quantization( numOFLevels , img ) ;

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        Decompress();

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Quantizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quantizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quantizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quantizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quantizer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
