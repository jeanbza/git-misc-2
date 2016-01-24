require 'sinatra'

class BarSimulator < Sinatra::Base
  get '/bar' do
    'BAR: hello world'
  end
end
