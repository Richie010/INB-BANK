import React, { useState } from "react";


import "bootstrap/dist/css/bootstrap.min.css";
 // Custom CSS for flipping effect
import { SavingLogin } from "./Login";

function LoginPage() {
  const [showSavings, setShowSavings] = useState(false);
  const [showCurrent, setShowCurrent] = useState(false);

  return (
    <div className="flip-card-outer">
      <div className="flip-card-inner">
        <div className="card front">
          <div 
            className={`card ${showSavings ? 'flipped' : ''}`} 
            onMouseEnter={() => setShowSavings(true)}
            onMouseLeave={() => setShowSavings(false)}
          >
            <div className="card-body d-flex justify-content-center align-items-center">
            </div>
            <div className="card-back">
              <SavingLogin />
            </div>
         </div>
        </div>
        
      </div>
    </div>
  );
}

export default LoginPage;