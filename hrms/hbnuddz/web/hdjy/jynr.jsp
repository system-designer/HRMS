<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : jynr
    Created on : 2013-2-18, 17:25:37
    Author     : evance
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html >
<html>
    <head>
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<z:path/>web/hdjy/jynr.css"/>
        <script type="text/javascript" src="<z:path/>web/hdjy/jynr.js"></script>
        <title>JSP Page</title>
        <script type="text/javascript">
            var path='<z:path/>';
        </script>
    </head>
    <body>
        <input type="hidden" id="get_hdid" value="${hdid}"/>
        <c:choose>
            <c:when test="${fn:length(hdjylist)>0}">
                <div>
                    <div class="nr1">
                        <ul>
                            <c:forEach var="hdjy" items="${hdjylist}">
                                <li>
                                    <img onclick="showIMG('${hdjy.jytp}')" src="<z:path/>userfiles${hdjy.jytp}" alt="${hdjy.jybt}"/><em>${hdjy.jybt}</em><br/>
                                    <em>${hdjy.jysj}</em>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div id="jyImgDiv" style="display:none">
                    <div id="closeICON"><img src="<z:path/>web/image/close-3.png" onclick="closeIMG()"  onError="javascript:this.src='<z:path/>userfiles/azxmxx/nopic.jpg';"/></div>
                    <div></div>
                </div>
                <!--翻页-->
                <div class="jy_fy" style="margin-top: 20px;">
                    <span class="fy_ys" style="  background:#e5edf2; margin-left: 3px;padding:3px 5px;border:1px solid #c2d5e3;"><a href="<z:path/>ShowHdjyIndexAction.jsp?mode=SHOWHDJYLIST&hdid=${hdid}&page=1" style="text-decoration: none;color:black;">首页</a></span>
                    <span class="fy_ys" style="  background:#e5edf2; margin-left: 3px;padding:3px 5px;border:1px solid #c2d5e3;"><a href="<z:path/>ShowHdjyIndexAction.jsp?mode=SHOWHDJYLIST&hdid=${hdid}&page=${page<=1?1:page-1}"style="text-decoration: none;color:black;">上一页</a></span>
                    <span class="fy_ys" style="  background:#e5edf2; margin-left: 3px;padding:3px 5px;border:1px solid #c2d5e3;"><a href="<z:path/>ShowHdjyIndexAction.jsp?mode=SHOWHDJYLIST&hdid=${hdid}&page=${page>=totalpages?totalpages:page+1}"style="text-decoration: none;color:black;">下一页</a></span>
                    <span class="fy_ys" style="  background:#e5edf2; margin-left: 3px;padding:3px 5px;border:1px solid #c2d5e3;"><a href="<z:path/>ShowHdjyIndexAction.jsp?mode=SHOWHDJYLIST&hdid=${hdid}&page=${totalpages}"style="text-decoration: none;color:black;">尾页</a></span>
                    第<span id="page">${page}</span>/<span id="totalpages">${totalpages}</span>页
                    <span><input type="text" size="1" id="goto_page"/></span>
                    <span class="fy_ys" id="goto" onclick="gotoPage()" style="  background:#e5edf2; margin-left: 3px;padding:3px 5px;border:1px solid #c2d5e3;">goto</span>
                </div> 
            </c:when>
            <c:otherwise>
                暂时没有相关信息
            </c:otherwise>
        </c:choose>
    </body>
</html>
