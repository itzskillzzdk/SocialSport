package chartSports;

import controller.Controller;
import controller.data_access_object.DataDAO;
import modules.donnees.DonneeTennis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import views.ContainerFrame;
import views.panels.GraphiqueSports;
import views.panels.show_graph_panel.TennisShowGraphPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class ChartTennis extends JFrame {

    private static final long serialVersionUID = 1L;
    private Controller controller;
    public ChartTennis(String title, Controller controller, EnumType type) {
        super(title);
        this.controller = controller;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setPreferredSize(GraphiqueSports.graphFrameDim);
        JPanel chartPanel = new JPanel(new GridLayout(1, 3));
        remove(chartPanel);
        chartPanel.revalidate();
        chartPanel.repaint();
        switch(type) {
            case JEUX:
                CategoryDataset dataset = createDatasetNbjeuxGagnes();
                JFreeChart chart = createChartNbJeuxGagnes(dataset);
                ChartPanel chartPanelDuree = new ChartPanel(chart);
                chartPanel.add(chartPanelDuree);
                break;
            case SERVICE:
                CategoryDataset datasetNbServicesReussi = createDatasetNbServicesReussi();
                JFreeChart chartNbServicesReussi = createChartNbServicesReussi(datasetNbServicesReussi);
                ChartPanel chartPanelNbServicesReussi = new ChartPanel(chartNbServicesReussi);
                chartPanel.add(chartPanelNbServicesReussi);
                break;
            case DUREE:
                XYSeriesCollection datasetDuree = createDatasetDuree();
                JFreeChart chartHauteur = createChartDuree(datasetDuree);
                ChartPanel chartPanelHauteur = new ChartPanel(chartHauteur);
                chartPanel.add(chartPanelHauteur);
                break;
        }
        setContentPane(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private CategoryDataset createDatasetNbServicesReussi() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Utiliser une TreeMap pour trier les données par date croissante
        TreeMap<Date, Integer> dureeByDate = new TreeMap<>();
        List<DonneeTennis> donnees = DataDAO.getInstance().getDataTennis(controller.getCurrentUser());
        for (DonneeTennis donnee : donnees) {
            // Ajouter la durée à la valeur existante pour ce jour
            Date date = donnee.getDate();
            Integer nbServiceReussi = donnee.getNbServiceReussi();
            dureeByDate.merge(date, nbServiceReussi, Integer::sum);
        }

        // Ajouter les données à l'ensemble de données
        for (Map.Entry<Date, Integer> entry : dureeByDate.entrySet()) {
            dataset.addValue(entry.getValue(), "Nombre de services réussis", entry.getKey().toString());
        }

        return dataset;
    }

    private CategoryDataset createDatasetNbjeuxGagnes() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Utiliser une TreeMap pour trier les données par date croissante
        TreeMap<Date, Integer> dureeByDate = new TreeMap<>();
        List<DonneeTennis> donnees = DataDAO.getInstance().getDataTennis(controller.getCurrentUser());
        for (DonneeTennis donnee : donnees) {
            // Ajouter la durée à la valeur existante pour ce jour
            Date date = donnee.getDate();
            Integer nbJeuxGagness = donnee.getJeuxGagne();
            dureeByDate.merge(date, nbJeuxGagness, Integer::sum);
        }

        // Ajouter les données à l'ensemble de données
        for (Map.Entry<Date, Integer> entry : dureeByDate.entrySet()) {
            dataset.addValue(entry.getValue(), "Nombre de jeux gagnés", entry.getKey().toString());
        }

        return dataset;
    }

    private XYSeriesCollection createDatasetDuree() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Créer une série pour la hauteur
        XYSeries series = new XYSeries("Durée");

        // Récupérer les données de Tennis de l'utilisateur
        List<DonneeTennis> donnees = DataDAO.getInstance().getDataTennis(controller.getCurrentUser());

        // Ajouter les données à la série
        for (DonneeTennis donnee : donnees) {
            float dureeMinutes = Float.parseFloat(donnee.getDuree());
            series.add(donnee.getDate().getTime(), dureeMinutes);
        }

        // Ajouter la série au jeu de données
        dataset.addSeries(series);

        return dataset;
    }


    private JFreeChart createChartDuree(XYDataset dataset) {
        return ChartFactory.createXYLineChart(
                "Durée de tennis par jour",
                "Date",
                "Durée (min)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }
    private JFreeChart createChartNbServicesReussi(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de services réussis de tennis par jour",
                "Date",
                "Nombre de services réussis",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart;
    }
    private JFreeChart createChartNbJeuxGagnes(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de jeux gagnés de tennis par jour",
                "Date",
                "Nombre de jeux gagnés",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        return chart;
    }
    public enum EnumType {
        DUREE,
        SERVICE,
        JEUX;
    }
}
