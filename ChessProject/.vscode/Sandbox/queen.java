
		if(pieceName.contains("Queen")){
			Boolean intheway = false;

			if((( landingX < 0) || (landingX > 7 )) || ((landingY < 0 ) || (landingY > 7))){
					validMove = false;
			}
			else{
				if(((Math.abs(startX - landingX) !=0) && (Math.abs(startY - landingY) == 0 )) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY)!= 0))){				
					if(Math.abs(startX - landingY) != 0){
						xMovement = Math.abs(startX - landingX);
						if(startX - landingX > 0){
							for(int i=0; i < xMovement; i++){
								if(piecePresent(initialX - (i*75), e.getY())){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}
						else{
							for( int i=0 ; i < xMovement ; i++){
								if (piecePresent(initialX + (i*75), e.getY())){
									intheway = true ;
									break ;
								}
								else{
									intheway = false;
								}
							}
						}
					}  //============
					else{
						yMovement = Math.abs(startY - landingY);
						if(startY - landingY > 0){
							for(int i=0 ; i < yMovement ; i++){
								if(piecePresent(e.getX(), initialY + (i*75))){
									intheway = true ;
									break ;
								}
								else{
									intheway = false;
								}
							}
						}
						else{
							for(int i=0 ; i < yMovement ; i++){
								if(piecePresent(e.getX(), initialY+(i*75))){
									intheway = true;
									break;
								}
								else{
									intheway = false;
								}
							}
						}

					}

					if(intheway){
						validMove = false ;
					}
					else{
						if(piecePresent(e.getX(), (e.getY()))){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(), e.getY())){
									validMove = true ;
								}
								else{
									validMove = false;
								}
							}
							else{
								if(checkBlackOponent( e.getX() , e.getY())){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
						}
						else{
							validMove = true ;
						}
					}	
				}
				else{
					validMove = true;
				}
			}
		}