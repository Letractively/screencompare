// ToTop2.cpp : Defines the class behaviors for the application.
//
#include "stdafx.h"
#include "ToTop2.h"
#include "ToTop2Dlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif



WCHAR* target;

BOOL CALLBACK callback(HWND hwnd, LPARAM lparam) {
	TCHAR title[500];
	ZeroMemory(title, sizeof(title));
	int titleLen = GetWindowText(hwnd, title, sizeof(title)/sizeof(title[0]));

	using namespace std;
	string jo1((char*)title, wcslen(title)*2);
	string jo2((char*)target, wcslen(target)*2);

	int jo1l = jo1.length();
	int jo2l = jo2.length();
	
	if (jo1.find(jo2) != string::npos) {
		int r = SetForegroundWindow(hwnd);
		r = BringWindowToTop(hwnd);
		return FALSE;
	}
	return TRUE;
}

// CToTop2App

BEGIN_MESSAGE_MAP(CToTop2App, CWinApp)
	ON_COMMAND(ID_HELP, &CWinApp::OnHelp)
END_MESSAGE_MAP()


// CToTop2App construction

CToTop2App::CToTop2App()
{
	// TODO: add construction code here,
	// Place all significant initialization in InitInstance
}


// The one and only CToTop2App object

CToTop2App theApp;


// CToTop2App initialization

BOOL CToTop2App::InitInstance()
{
	target = &m_lpCmdLine[1];

	CWinApp::InitInstance();

	CToTop2Dlg dlg;
	m_pMainWnd = &dlg;
	EnumWindows(callback, 0);

	exit(0);

	return FALSE;
}



