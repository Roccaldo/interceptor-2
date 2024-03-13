package co.develhope.interceptor2.interceptors;

import co.develhope.interceptor2.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> monthList = new ArrayList<>(Arrays.asList(
            new Month(1, "January", "Gennaio", "Januar"),
            new Month(2, "February", "Febbraio", "Februar"),
            new Month(3, "March", "Marzo", "Marsch")
    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumbRequest = request.getHeader("monthNumber");
        if (monthNumbRequest == null) {
            response.setStatus(404);
            return false;
        }

        Integer monthNumb = Integer.parseInt(monthNumbRequest);
        Optional<Month> foundMonth = monthList.stream().filter(month -> {
            return month.getMonthNumber().equals(monthNumb);
        }).findFirst();

        if (foundMonth.isPresent()) {
            response.setStatus(202);
            request.setAttribute("month", foundMonth.get());
        } else {
            Month emptyMonth = new Month(0, "nope", "nope", "nope");
            request.setAttribute("month", emptyMonth);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
