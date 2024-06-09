package com.ues.fia.bad115.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.ues.fia.bad115.clase.Transaccion;
import com.ues.fia.bad115.component.NavBar;
import com.ues.fia.bad115.service.TransaccionService;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataLabels;
import com.vaadin.flow.component.charts.model.PlotOptionsPie;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.charts.model.style.Style;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import java.util.List;

@Route("reportes")
@PageTitle("Reportes | Biblioteca Central de Centro America")
public class ReporteView extends VerticalLayout {
    NavBar navegacion = new NavBar();
    @Autowired
    private TransaccionService transaccionService;

    public ReporteView(TransaccionService transaccionsService) {
        setClassName("login");
        H1 titulo = new H1("Reportes");
        titulo.setClassName("titulo");
        ComboBox<String> comboBoxCategoria = new ComboBox<>();
        comboBoxCategoria.setItems("Finanzas", "Recursos", "Prestamos");
        comboBoxCategoria.setPlaceholder("Seleccione una categoria");

        ComboBox<String> comboBoxReporte = new ComboBox<>();
        List<String> reportesFinanzas = List.of("Porcentaje de transacciones", "Monto de transacciones");
        List<String> reportesRecursos = List.of("Recursos por Categoria", "Recursos por subcategoria");
        List<String> reportesPrestamos = List.of("Porcentaje Recursos Prestados", "Recursos Prestados por Usuario");
        comboBoxReporte.setPlaceholder("Seleccione un reporte");
        comboBoxReporte.setReadOnly(true);
        comboBoxCategoria.addValueChangeListener(event -> {
            if (event.getValue().equals("Finanzas")) {
                comboBoxReporte.setReadOnly(false);
                comboBoxReporte.setItems(reportesFinanzas);
            } else if (event.getValue().equals("Recursos")) {
                comboBoxReporte.setReadOnly(false);
                comboBoxReporte.setItems(reportesRecursos);
            } else if (event.getValue().equals("Prestamos")) {
                comboBoxReporte.setReadOnly(false);
                comboBoxReporte.setItems(reportesPrestamos);
            }
        });
        this.transaccionService = transaccionsService;
        // Obtener las transacciones de tipo entrada y salida
        List<Transaccion> entradas = transaccionService.getTransaccionTipo("Entrada");
        List<Transaccion> salidas = transaccionService.getTransaccionTipo("Salida");

        // Gráfico de pastel para mostrar el porcentaje de transacciones
        Chart qtyChart = new Chart(ChartType.PIE);
        Configuration qtyConf = qtyChart.getConfiguration();
        PlotOptionsPie qtyOptions = new PlotOptionsPie();
        qtyOptions.setInnerSize("0");
        qtyOptions.setSize("75%");
        qtyOptions.setCenter("50%", "50%");
        DataLabels qtyLabels = new DataLabels();
        qtyLabels.setEnabled(true);
        qtyLabels.setFormat("{point.name}: {point.percentage:.1f} %");
        Style style = new Style();
        style.setColor(SolidColor.WHITE);
        style.setFontSize("15px");
        style.setTextShadow(null);
        qtyLabels.setStyle(style);
        qtyOptions.setDataLabels(qtyLabels);
        qtyConf.setPlotOptions(qtyOptions);
        qtyChart.getStyle().setColor("white");
        qtyConf.getChart().setBackgroundColor(new SolidColor("#233348"));
        DataSeries qtyData = new DataSeries();
        qtyData.add(new DataSeriesItem("Ingresos", entradas.size()));
        qtyData.add(new DataSeriesItem("Costos", salidas.size()));
        qtyConf.setSeries(qtyData);
        qtyChart.getStyle().setWidth("fit-content");

        // Gráfico de pastel para mostrar el monto de transacciones
        Chart amountChart = new Chart(ChartType.PIE);
        Configuration amountConf = amountChart.getConfiguration();
        PlotOptionsPie amountOptions = new PlotOptionsPie();
        DataLabels amountLabels = new DataLabels();
        amountLabels.setEnabled(true);
        amountLabels.setFormat("{point.name}: ${point.y:.2f} ");
        amountLabels.setStyle(style);
        amountOptions.setDataLabels(amountLabels);
        amountConf.setPlotOptions(amountOptions);
        amountConf.getChart().setBackgroundColor(new SolidColor("#233348"));
        amountChart.getStyle().setWidth("fit-content");
        DataSeries dataSeries2 = new DataSeries();
        Float totalIngresos = 0f;
        Float totalCostos = 0f;
        for (Transaccion transaccion : entradas) {
            totalIngresos += transaccion.getMonto();
        }
        for (Transaccion transaccion : salidas) {
            totalCostos += transaccion.getMonto();
        }
        dataSeries2.add(new DataSeriesItem("Ingresos", totalIngresos));
        dataSeries2.add(new DataSeriesItem("Costos", totalCostos));
        amountConf.setSeries(dataSeries2);
        H3 porcentaje = new H3("Porcentaje de transacciones");
        porcentaje.setClassName("reporte");
        H3 monto = new H3("Monto de transacciones");
        monto.setClassName("reporte");
        VerticalLayout chartLayout = new VerticalLayout();
        chartLayout.setClassName("div-reporte");
        chartLayout.add(porcentaje, qtyChart);
        VerticalLayout chartLayout2 = new VerticalLayout();
        chartLayout2.setClassName("div-reporte");
        chartLayout2.add(monto, amountChart);

        HorizontalLayout layout = new HorizontalLayout(chartLayout, chartLayout2);
        layout.getStyle().setWidth("100%");
        layout.getStyle().set("justify-content", "center");
        layout.getStyle().set("align-items", "center");
        layout.getStyle().set("margin", "0");
        layout.getStyle().set("padding", "0");

        comboBoxCategoria.getElement().getThemeList().add("dark");
        comboBoxReporte.getElement().getThemeList().add("dark");
        HorizontalLayout comboLayout = new HorizontalLayout(comboBoxCategoria, comboBoxReporte);
        comboLayout.getStyle().set("justify-content", "center");
        comboLayout.getStyle().set("align-items", "center");
        comboLayout.getStyle().set("margin", "0");
        comboLayout.getStyle().set("padding", "0");

        add(navegacion, titulo, comboLayout, layout);
    }

    private VerticalLayout reporteFinanzas() {
        return new VerticalLayout();
    }

}
