require_relative '../spec_helper'

describe '/number_generator' do
  it 'returns 200 status code' do
    get '/number_generator'
    expect(last_response).to be_ok
  end

  # I should have been removed when the previous test was added!
  it 'does not returns 3' do
    get '/number_generator'
    expect(last_response.body).to_not eq '3'
  end

  it 'returns 5' do
    get '/number_generator'
    expect(last_response.body).to eq '5'
  end
end
