package py4java;
import py4j.GatewayServer;

import java.io.IOException;

import hex.genmodel.MojoModel;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;

public class EntryPoint {

    String modelPath = "/Users/maravenag/Desktop/py4java_poc/model/GBM_model_python_1584560622772_1.zip";
    private EasyPredictModelWrapper predictModelWrapper;

    public EntryPoint() throws IOException {
        MojoModel model = MojoModel.load(modelPath);
        this.predictModelWrapper = new EasyPredictModelWrapper(model);
    }

    public String predict(String mfr, Integer calories, Integer protein) throws PredictException {
        RowData modelFeatures = new RowData();
        modelFeatures.put("mfr", mfr);
        modelFeatures.put("calories", calories.toString());
        modelFeatures.put("protein", protein.toString());
        BinomialModelPrediction prediction = this.predictModelWrapper.predictBinomial(modelFeatures);
        return prediction.label;
    }

    public static void main(String[] args) throws IOException {
        GatewayServer gatewayServer = new GatewayServer(new EntryPoint());
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }

}