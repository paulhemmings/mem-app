<style>
			/*
			 * Global
			 */ 
			 
			body
			{
				font-family: Helvetica Neue,Arial,Helvetica,sans-serif;
				font-size: 75%;
				line-height: 1.5;
				background-color:#E7EBF2;
			}

			li
			{
				list-style-image: none;
				list-style-type: none;
			}

			a
			{
				text-decoration: none;
				color: #095a99;
			}

			a:visited
			{
				color: #4675a0;
			}

			a:hover, a:active
			{
				color: #f60;
			}			 
			
			/* 
			 * standard 
			 */			 
			
			.roundedBox
			{
				-webkit-box-shadow: #244766 5px 5px 10px;
				-moz-box-shadow: #244766 10px 10px 10px;
				box-shadow: #244766 10px 10px 10px;
				
				-webkit-border-radius: 12px;
				-moz-border-radius: 12px;
				border-radius: 12px;	
			}
			.raised
			{
				border-bottom:solid 2px black;
			}
			
			.readonly {
				-moz-user-modify: read-only;
				-webkit-user-modify: read-only;
			}
			.readwrite {
				-moz-user-modify: read-write;
				-webkit-user-modify: read-write;
			}			
			
			.editableSection{
				background-color:#DDDDDD;
			}
			
			.button{
				display:inline-block;
				background-color: #F3F4F7;
				color:#095a99;
				border:solid 1px #C4CCE0;
				padding:5px;
				vertical-align:middle;
				text-align:center;
				cursor:pointer;
				margin: 3px 0;
			}
			
			.button:hover, .button:active
			{
				color: #f60;
			}				
						
			/*
			 * Page specific 
			 */
			
		
			#mainPage{
				margin: 5px;
				padding: 10px;
				position: relative;
				min-height:500px;
			}
			
			#mainStoryBlock{
				display:inline-block;
			}
			
			#storyHelp{
				font-family: georgia;
			    background-color: #F3F4F7;
			    border:solid 1px #C4CCE0;
			    clear: both;
			    margin: 5px;
			    padding: 10px;
			    right: 200px;
			    width: 450px;	
			    color:#111;		
			}
			
			#friendHelp{
				font-family: georgia;
				color:#111;
			}
			
			/*
			 * Story section 
			 */
			
			.storySection{
			    background-color: #FFF;
			    border:solid 1px #C4CCE0;
				clear: both;
				padding: 10px;
				right: 200px;
				margin:5px;
				width:450px;
			}
			
			.newStory{
				padding-bottom:10px;
				border-bottom:solid 1px grey;
			}
			
			#yoursOrSharedChooser{
				width:100%;
				text-align:right;			
				padding-bottom: 10px;
				border-bottom: 1px solid #000;
				cursor:pointer;
			}
			
			#yoursOrSharedChooser.yours > a.yours{
				font-weight:bold;
			}
			#yoursOrSharedChooser.shared > a.shared{
				font-weight:bold;
			}			
			
			#existingStoriesSection{
			}
			
			#storyListContainer{				
			}
						
			.existingStory{
				border-bottom: 1px solid #C4CCE0;
			}
			
			.storyText{
				padding:5px;
				margin: 5px 0;		
				right:10px;
				max-height: 300px;
				overflow: auto;
				font-family: arial;				
			}
			
			.storyText.readwrite{
				background-color:#fff;
				border:1px solid #ccc;
				border:solid 1px #C4CCE0;
			}
			
			.storySharedList{
				padding:0;
				margin:0;
				/* padding: 0 10px 0 0; */
				text-align: right;		
				list-style: none;		
				background-color: #F3F4F7; /* #E7EBF2; */
			}	
			
			/*
			 * Comment Section
			 */

			.storyDiscussionList{				
				list-style: none;
				margin:0;
				padding:0;
				display:none;
			}
			
			.storyDiscussionItem{
				background-color: #EEEEEE;
				vertical-align:top;
				border-bottom:solid 1px #ccc;
				font-family: georgia;
				padding:3px;
				position:relative;
			}
						
			.storyDiscussionItem img{
				padding:5px 10px 5px 5px;
				height: 25px;
			}
			
			.storyDiscussionItem > div.storyDiscussionItemText{
				vertical-align:top;
				display:inline;
			}			
			
			.storyDiscussionItem > div.readwrite{
				vertical-align:top;
				display:inline-block;
				background-color:#fff;
				width:100%;
			}
			
			.storyDiscussionItemDelete{
				cursor:pointer;
				position:absolute;
				right:2px;
				top:2px;
				color:#aaa;
				display:none; /* not yet working */
			}		
			
			.storyDiscussionItemEditable{
				width:100%;
				resize:vertical;
				max-height:200px;
			}		
			
			.storeStoryChooser{
				display:none;
				width:100%;
				text-align:right;
				color:blue;
				cursor:pointer;
			}
			
			.storyText.readwrite + .storySharedList + .storeStoryChooser{
				display:block;
			}
			
			.storeStoryChooser.newStoryIeHack{
				display:block;
			}
			
			.commentDisplayChoose{
				width:100%;
				text-align:right;
				background-color: #EEEEEE;
				vertical-align:bottom;
				cursor:pointer;
			}
			.commentDisplayChoose img{
				padding:2px;
			}
			
			.addCommentChooserRow{
				width:100%;
				text-align:right;
			}
			
			/*
			 * Story Friend section
			 */
			 
			.friendItem.attached{
				display:inline-block;
				padding:5px;
				cursor:pointer;
			}
			
			.friendItem.attached div{
				display:none;
			}
			
			/*
			 * Friend Section
			 */
			
			#friendSection{
			    background-color: #FFF;
			    border:solid 1px #C4CCE0;
			    
			    padding: 6px;
			    margin:5px;
			    width:150px;
				float:left;

				
				/*			    
				bottom: 0;
				position: absolute;
				right: 0;
				top: 0;
				*/ 		
			}
			
			#friendFilter{
				width:100%;
				margin-bottom:10px;
			}
			
			#friendContainer{
				padding:0;
				margin:0;
				overflow: auto;
				height:600px;
			}
			
			#friendContainer .friendItem{
				cursor:pointer;
				font-family:arial;
				background-color: #F3F4F7;
				border:solid 1px #ccc;
				margin-top:5px;
				margin-bottom:5px;
				
			}
			
			#friendContainer .friendItem div{
				padding-left:2px;
				font-family:georgia;
				background-color:#CEE0ED;
				
			}
			
			#friendContainer .friendItem img{
				display:inline-block;
				padding:2px;
			}
</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
