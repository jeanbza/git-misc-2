ENV['RACK_ENV'] = 'test'

require 'bundler'
require 'rubygems'
require 'rack/test'
require 'rspec'
require 'sinatra'

require File.expand_path '../../app.rb', __FILE__

Bundler.require :default

module RSpecMixin
  include Rack::Test::Methods
  def app()
    Sinatra::Application
  end
end

RSpec.configure do |config|
  config.filter_run :focus
  config.run_all_when_everything_filtered = true
  config.default_formatter = 'doc'
  config.order = :random
  config.include RSpecMixin

  Kernel.srand config.seed
end
