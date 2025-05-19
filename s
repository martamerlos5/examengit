[1mdiff --git a/PersonaDAO.java b/PersonaDAO.java[m
[1mindex 4bedd54..cfa978a 100644[m
[1m--- a/PersonaDAO.java[m
[1m+++ b/PersonaDAO.java[m
[36m@@ -22,7 +22,7 @@[m [mpublic class PersonaDAO {[m
                     devolver = true;[m
                 }[m
             } catch (SQLException ex) {[m
[31m-                System.out.println("Error al conectarse a la BBDDS");[m
[32m+[m[32m                System.out.println("Error al conectarse a la BBDD");[m
             } finally {[m
                 try {[m
                     con.close();[m
