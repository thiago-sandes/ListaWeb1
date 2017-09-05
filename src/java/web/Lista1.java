/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thiago José
 */
@WebServlet(name = "Lista1", urlPatterns = {"/Lista1"})
public class Lista1 extends HttpServlet {
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
            Connection conn;
            String matricula = request.getParameter("matricula");
            String saida = null;
            int j = 0;
            int var = 0;
            int number = 0;
            boolean success = false;
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Lista 1</title>");
            out.println("<style type=\"text/css\">" +
                "<!-- " +
                "body {background-color:beige; color:black; font-size:90%}"+
                "td   {font-size: 90%; background-color:white; color: black}" +
                "input {background-color:white}" +
                "//--></style>");
            out.println("</head>");
            out.println("<body>");
                try {
	            Class.forName(JDBC_DRIVER );
	            conn = DriverManager.getConnection(DATABASE_URL, 
                            "root", "dd4dyvtr" );
	            Statement st = conn.createStatement();
                    Statement stSec = conn.createStatement();
                    
                    ResultSet rec = st.executeQuery(
	                "SELECT disciplinas.cod_disciplina, disciplinas.nome_disciplina, disciplinas.carga_horaria,turmas.diahorario1_turma, turmas.diahorario2_turma, turmas.diahorario3_turma, turmas.cod_turma " +
	                "FROM disciplinas, turmas " +
                        "WHERE (disciplinas.cod_disciplina = turmas.cod_disciplina) " +        
                        "ORDER BY disciplinas.cod_disciplina");
                    
                    ResultSet recSec = stSec.executeQuery(
	                "SELECT alunos.matricula_aluno " +
	                "FROM alunos " +        
                        "ORDER BY alunos.matricula_aluno");
                    
                    while(recSec.next()){
                        j++;
                    }
                    
                    recSec.first();
                    
                    for(var = 0; var < j; var++){                        
                        if(matricula.equals(recSec.getString(1))){
                            number ++;
                        }else{                           
                            success = false;
                        }
                        recSec.next();
                    }
                    
                    
                    if(number > 0)
                        success = true;
                    
                    recSec.first();
                    
                    if(success == true){
                        out.println("<h1>Lista 1</h1>");
                        out.println("<h2>Tabela Disciplinas</h2>");

                        out.println("<table border=1><tr>");
                        out.println("<td><b>Codigo da disciplina</b></td><td><b>Nome da disciplina</b></td>" +
                                "<td><b>Carga Horária Semanal</b></td>" +
                                "<td><b>Horário 1</b></td>" + "<td><b>Horário 2</b></td>" +
                                "<td><b>Horário 3</b></td>"+
                                "<td><b>Código da turma</b></td>"  + "</tr>");
                        out.println("<form action=\"Lista1_2\"> ");
                        int i = 0;
                        while(rec.next()) {
                            out.println("<tr><td><input type=\"text\" name=\"cod_disciplina\" value=\""
                                    + rec.getString(1) + "\" size=\"8\" readonly=\"true\" /><br /></td>"
                                    + "<td><input type=\"text\" name=\"nome_disciplina\" value=\""
                                    + rec.getString(2) + "\" size=\"20\" readonly=\"true\" /><br /></td>"
                                    + "<td><input type=\"text\" name=\"carga_horaria\" value=\""
                                    + rec.getString(3) + "\" size=\"1\" readonly=\"true\"/><br /></td>"
                                    + "<td><input type=\"text\" name=\"diahorario1_turma\" value=\""
                                    + rec.getString(4) + "\" size=\"6\" readonly=\"true\"/><br /></td>" 
                                    + "<td><input type=\"text\" name=\"diahorario2_turma\" value=\""
                                    + rec.getString(5) + "\" size=\"6\" readonly=\"true\"/><br /></td>" 
                                    + "<td><input type=\"text\" name=\"diahorario3_turma\" value=\""
                                    + rec.getString(6) + "\" size=\"6\" readonly=\"true\"/><br /></td>"                                 
                                    + "<td><input type=\"radio\" name=\"radioB\" value=\"" + rec.getString(7) + "\"/>" + rec.getString(7) + "</td></tr>");


                            i++;
                        }
                        String num = Integer.toString(i);
                        out.println("</table>");
                        out.println("<br/><input type='submit' value='Selecionar' name='enviar' />");
                        out.println("  <input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\" />");
                        out.println("<input type='hidden' name='numRec' value='"
                                  + num + "' />");
                        out.println("</form>");
                        st.close();
                        stSec.close();
                    }else{
                        out.println("Matrícula não encontrada. Tente novamente.");
                        out.println("<br /><input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\" />");
                        number = 0;
                    }    
                } catch (SQLException s) {
                    out.println("SQL Error: " + s.toString() + " "
                        + s.getErrorCode() + " " + s.getSQLState()+"<br />");
                    out.println("Você fez algo errado . . . <br />");
                    out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\" />");
                        
                } catch (Exception e) {
                    out.println("Error: " + e.toString()
                        + e.getMessage()+ "<br />");
                    out.println("Ish ... Algum ET pousou aqui. É melhor você voltar. <br />");
                    out.println("<input type=\"button\" value=\"Voltar\" onClick=\"history.go(-1)\" />");
                    
                }
	    
        out.println("</body>");
        out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
