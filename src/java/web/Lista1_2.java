/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thiago José
 */
@WebServlet(name = "Lista1_2", urlPatterns = {"/Lista1_2"})
public class Lista1_2 extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";        
    static final String DATABASE_URL = "jdbc:mysql://localhost/listaweb1";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");         
        PrintWriter out = response.getWriter();
        String numRec = request.getParameter("numRec");
        int num = Integer.parseInt(numRec); 
        String cod_turma = request.getParameter("radioB");
        try {   
            Connection conn;
            Class.forName(JDBC_DRIVER );
            conn = DriverManager.getConnection(DATABASE_URL, 
                          "root", "dd4dyvtr" );
            Statement st = conn.createStatement();           
            ResultSet rec = st.executeQuery(
            "SELECT disciplinas.cod_disciplina, disciplinas.nome_disciplina, disciplinas.carga_horaria, alunos.matricula_aluno, alunos.nome_aluno, alunos.curso FROM alunos, disciplinas " +
            "WHERE (cod_turma='" + cod_turma + "') AND (disciplinas.cod_disciplina = alunos.cod_disciplina)");
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Lista1_2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Dados da turma</h1>");
            rec.next();
            out.println("<h2>Disciplina selecionada:" + "<br/>Código: " + rec.getString(1) + "<br/>Nome: " + rec.getString(2) + "<br/>Carga horária: " + rec.getString(3) + "</h2>");
            
            rec.previous();
            
            out.println("<form>");
            out.println("<table border=1><tr>");
            out.println("<td><b>Matrícula do Aluno</b></td><td><b>Nome do Aluno</b></td>" +
            "<td><b>Curso</b></td></tr>");
            while(rec.next()){
                out.println("<tr><td><input type=\"text\" name=\"matricula_aluno\" value=\""
                + rec.getString(4) + "\" size=\"12\" readonly=\"true\" /></td>"
                + "<td><input type=\"text\" name=\"nome_aluno\" value=\""
                + rec.getString(5) + "\" size=\"40\" readonly=\"true\" /></td>"
                + "<td><input type=\"text\" name=\"curso\" value=\""
                + rec.getInt(6) + "\" size=\"3\" readonly=\"true\"/></td></tr>");                                 
            }
            
            out.println("</table>");
            out.println("<br /><input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\" />");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
            
            st.close();
            conn.close();
            
          }catch (SQLException s) {
                out.println("SQL Error: " + s.toString() + " "
	         + s.getErrorCode() + " " + s.getSQLState()+ "<br />");
                out.println("Você precisa selecionar alguma coisa . . .<br />");
                out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\" />");
	  }catch (Exception e) {
                out.println("Error: " + e.toString()
	         + e.getMessage()+ "<br />");
                out.println("Ish ... Algum ET pousou aqui. É melhor você voltar. <br />");
                out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\" />");
                    
          }
        out.close();
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Lista1_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Lista1_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
