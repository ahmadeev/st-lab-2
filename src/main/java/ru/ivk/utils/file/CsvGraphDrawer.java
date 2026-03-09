package ru.ivk.utils.file;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.ivk.function.AbstractFunction;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvGraphDrawer {
    private final String INPUT_DIR;
    private final CsvSeparators SEPARATOR;

    public CsvGraphDrawer(String inputDir, CsvSeparators separator) throws IOException {
        INPUT_DIR = inputDir;
        SEPARATOR = separator;

        Path dir = Paths.get(INPUT_DIR);

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
    }

    public void process(AbstractFunction fun) {
        try {
            final String filename = fun.toString();
            final String path = FileCommonUtilities.getFilePath(INPUT_DIR, filename, FileExtensions.CSV);
            List<BigDecimal[]> data = readCsv(path);
            XYSeriesCollection dataset = buildDataset(data, filename);
            JFreeChart chart = createChart(dataset, filename);
            JFrame window = createWindow(chart, filename);

            window.setVisible(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<BigDecimal[]> readCsv(String path) throws IOException {
        List<BigDecimal[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR.getSeparator());

                BigDecimal x = new BigDecimal(parts[0]);
                BigDecimal y = new BigDecimal(parts[1]);

                data.add(new BigDecimal[]{x, y});
            }
        }

        return data;
    }

    private XYSeriesCollection buildDataset(List<BigDecimal[]> data, String functionName) {
        XYSeries series = new XYSeries(functionName);

        for (BigDecimal[] point : data) {
            series.add(point[0], point[1]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset, String functionName) {
        return ChartFactory.createXYLineChart(
                functionName,
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private JFrame createWindow(JFreeChart chart, String functionName) {
        return new JFrame() {{
            setTitle(String.format("Function graph (%s)", functionName));

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);

            ChartPanel panel = new ChartPanel(chart);
            setContentPane(panel);
        }};
    }
}
