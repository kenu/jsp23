package kr.seoul.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

@WebServlet("/list.do")
public class ListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MVCBoardDAO dao = new MVCBoardDAO();
		Map<String, Object> map = new HashMap<>();
		
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !"".equals(pageTemp)) {
			pageNum = Integer.parseInt(pageTemp);
		}
		
		int pageSize = 2;
		int start = (pageNum - 1) * pageSize;
		int end = pageSize;
		map.put("start", start);
		map.put("end", end);
		int count = dao.selectCount(map);
		String pagingImg = BoardPage.pagingStr(count, pageSize, 5, pageNum, "/mvcboard/list.do");
		map.put("pagingImg", pagingImg);

		List<MVCBoardDTO> board = dao.selectListPage(map);
		req.setAttribute("map", map);
		req.setAttribute("count", count);
		req.setAttribute("board", board);
		
		req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req, resp);
	}
}
