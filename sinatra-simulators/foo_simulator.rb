require 'sinatra'

class FooSimulator < Sinatra::Base
  get '/foo' do
    'FOO: hello world'
  end
end
