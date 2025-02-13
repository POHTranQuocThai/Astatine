/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Voucher;

/**
 *
 * @author Ma Tan Loc - CE181795
 */
public class adCouponServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VoucherDAO vDAO = new VoucherDAO();
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if (action == null) {
            action = "list"; // Default action
        }

        if ("list".equals(action)) {
            List<Voucher> list = vDAO.getAllVoucher();
            request.setAttribute("couponList", list);
            request.getRequestDispatcher("/WEB-INF/adCoupon.jsp").forward(request, response);
            return; // Exit after forwarding
        }

        Voucher voucher = null;
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                voucher = vDAO.getVoucherById(id);
            } catch (NumberFormatException e) {
                response.sendRedirect("Coupon");
                return;
            }
        }

        if (voucher == null && !"create".equals(action)) {
            response.sendRedirect("Coupon");
            return;
        }

        switch (action) {
            case "edit":
                request.setAttribute("coupon", voucher);
                request.getRequestDispatcher("/WEB-INF/adCouponEdit.jsp").forward(request, response);
                break;
            case "create":
                request.getRequestDispatcher("/WEB-INF/adCouponCreate.jsp").forward(request, response);
                break;
            case "delete":
                int result = vDAO.deleteVoucher(Integer.parseInt(idParam));

                if (result > 0) {
                    response.sendRedirect("Coupon?action=list");
                } else {
                    request.setAttribute("errorMessage", "Unable to delete voucher");
                    request.getRequestDispatcher("/WEB-INF/adCoupon.jsp").forward(request, response);
                }
                break;
            default:
                response.sendRedirect("Coupon");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VoucherDAO vDAO = new VoucherDAO();
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if ("create".equals(action)) {
            String name = request.getParameter("voucherName");
            String discountStr = request.getParameter("discount");
            String expiry = request.getParameter("expiry");

            if (name == null || discountStr == null || expiry == null || name.isEmpty() || discountStr.isEmpty() || expiry.isEmpty()) {
                request.setAttribute("errorMessage", "All fields are required.");
                request.getRequestDispatcher("/WEB-INF/adCouponCreate.jsp").forward(request, response);
                return;
            }

            int discount;
            try {
                discount = Integer.parseInt(discountStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid discount value.");
                request.getRequestDispatcher("/WEB-INF/adCouponCreate.jsp").forward(request, response);
                return;
            }

            Voucher newVoucher = new Voucher(0, name, discount, expiry);
            int result = vDAO.createVoucher(newVoucher);

            if (result > 0) {
                response.sendRedirect("Coupon?action=list");
            } else {
                request.setAttribute("errorMessage", "Unable to create voucher");
                request.getRequestDispatcher("/WEB-INF/adCouponCreate.jsp").forward(request, response);
            }
            return;
        }

        if (idParam != null && !idParam.isEmpty()) {
            int voucherId;
            try {
                voucherId = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                response.sendRedirect("Coupon?action=list");
                return;
            }

            if ("edit".equals(action)) {
                String name = request.getParameter("voucherName");
                String discountStr = request.getParameter("discount");
                String expiry = request.getParameter("expiry");

                if (name == null || discountStr == null || expiry == null || name.isEmpty() || discountStr.isEmpty() || expiry.isEmpty()) {
                    request.setAttribute("errorMessage", "All fields are required.");
                    request.getRequestDispatcher("/WEB-INF/adCouponEdit.jsp").forward(request, response);
                    return;
                }

                int discount;
                try {
                    discount = Integer.parseInt(discountStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid discount value.");
                    request.getRequestDispatcher("/WEB-INF/adCouponEdit.jsp").forward(request, response);
                    return;
                }

                Voucher editedVoucher = new Voucher(voucherId, name, discount, expiry);
                int result = vDAO.editVoucher(editedVoucher);

                if (result > 0) {
                    response.sendRedirect("Coupon?action=list");
                } else {
                    request.setAttribute("errorMessage", "Unable to edit voucher");
                    request.getRequestDispatcher("/WEB-INF/adCouponEdit.jsp").forward(request, response);
                }
                return;
            }

//            if ("delete".equals(action)) {
//                System.out.println("v" + voucherId);
//                int result = vDAO.deleteVoucher(voucherId);
//                System.out.println("t" + result);
//                if (result > 0) {
//                    response.sendRedirect("Coupon");
//                } else {
//                    request.setAttribute("errorMessage", "Unable to delete voucher");
//                    request.getRequestDispatcher("/WEB-INF/adCouponDelete.jsp").forward(request, response);
//                }
//            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
