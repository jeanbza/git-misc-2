require 'sinatra'

module BaseSimulator
  get "#{@url}/health" do
    'Good to go'
  end
end
