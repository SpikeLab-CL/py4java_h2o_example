from h2o.estimators import H2OGradientBoostingEstimator
import h2o
h2o.init()

data = h2o.upload_file("data/sample.csv")
train_cols = ['mfr','calories','protein']
target = 'rating'
data[target] = data[target].asfactor()

model = H2OGradientBoostingEstimator()
model.train(x=train_cols, y=target, training_frame=data)
model.download_mojo(path="model/",get_genmodel_jar=True)
