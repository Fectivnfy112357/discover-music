import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Detail from './pages/Detail';

function App() {
  const [searchState, setSearchState] = useState({
    keyword: '',
    source: 'kw',
    results: [],
    loading: false,
    page: 1,
    hasSearched: false,
  });

  return (
    <BrowserRouter>
      <div className="min-h-screen font-sans antialiased text-slate-800 bg-gray-50">
        <Routes>
          <Route 
            path="/" 
            element={
              <Home 
                searchState={searchState} 
                setSearchState={setSearchState} 
              />
            } 
          />
          <Route path="/detail/:source/:rid" element={<Detail />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
