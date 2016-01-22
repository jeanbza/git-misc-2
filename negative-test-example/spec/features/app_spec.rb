require_relative '../spec_helper'

describe 'the app' do
  it 'does something' do
    HTTParty.get('http://localhost:8080/some_endpoint')
  end
end
