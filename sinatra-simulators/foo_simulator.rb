require 'sinatra'

require_relative './base_simulator'

class FooSimulator < Sinatra::Base
  @url = '/foo'

  include BaseSimulator

  get '/foo' do
    'FOO: hello world'
  end
end
