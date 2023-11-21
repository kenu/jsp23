package kr.seoul.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	public MVCBoardDAO() {
		super();
	}

	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "select count(*) from mvcboard";
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + 
					" like '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		List<MVCBoardDTO> board = new Vector<>();
		String query = "select * from mvcboard";
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + 
					" like '%" + map.get("searchWord") + "%'";
		}
		query += " order by idx desc limit ?, ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));

				board.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}

}
