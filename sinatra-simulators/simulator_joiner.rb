require 'sinatra'

require_relative './foo_simulator'
require_relative './bar_simulator'

class SimulatorJoiner < Sinatra::Base
  set :port, 8081

  use FooSimulator
  use BarSimulator

  get '/health' do
    'Simulators are up'
  end
end
