#include<stdio.h>

// Ham FIFO
void FIFO(int n, int frame, int *a, int *frames)
{
	for (int i = 0; i<frame; i++)
		frames[i] = -1;

	int i, j = 0, k, available, count = 0;

	printf( " Chuoi \t|Khung trang");
	for (int k = 0; k < frame - 1; k++)
		printf( "\t");
	printf( "|\n");

	for (i = 1; i <= n; i++)
	{
		printf(" %d\t", a[i]);
		available = 0;
		for (k = 0; k<frame; k++)
			if (frames[k] == a[i])
				available = 1; 

		if (available == 0) 
		{
			frames[j] = a[i];
			j = (j + 1) % frame;
			count++;
			printf( "|");

			for (k = 0; k < frame; k++)
				printf("%d\t", frames[k]);
			printf( "| F");
		}
		else
		{
			printf( "|");
			for (k = 0; k < frame; k++)
				printf("%d\t", frames[k]);
			printf( "|");
		}
		printf( "\n");
	}
	printf( "So trang loi la: %d\n", count);
}

// Tim vi tri thay OPT
int TimViTriOTP(int i, int n, int frame, int *a, int *frames)
{
	for (int f = 1; f <= frame; f++)
	{
		if (frames[f] == -1)
			return f;
	}
	int value = -1;
	int m[50];
	for (int z = 0; z <= 49; z++)
	{
		m[z] = -1;
	}
	for (int t = 1; t <= frame; t++)
	{
		int kt = 0;
		for (int z = i + 1; z <= n; z++)
		{
			if (frames[t] == a[z])
			{
				kt = 1;
				m[z] = frames[t];
				break;
			}
		}
		if (kt == 0)
			return t;
	}
	for (int n = 49; n >= 0; n--)
	{
		if (m[n] != -1)
		{
			value = m[n];
			break;
		}
	}
	for (int n = 1; n <= frame; n++)
	{
		if (value == frames[n])
			return n;
	}
}
// Ham OTP
void OTP(int n, int frame, int *a, int *frames)
{
	for (int i = 1; i <= frame; i++)
		frames[i] = -1;

	int i, j, available, count = 0;

	printf( " Chuoi\t|Khung trang");
	for (int k = 0; k<frame - 1; k++)
		printf( "\t");
	printf( "|\n");

	for (i = 1; i <= n; i++)
	{
		printf( " %d\t",a[i]);
		available = 0;
		for (int k = 1; k <= frame; k++)
			if (frames[k] == a[i])
				available = 1;
		if (available == 0)

		{
			int j = TimViTriOTP(i, n, frame, a, frames);
			frames[j] = a[i];
			count++;
			printf( "|");
			for (int k = 1; k <= frame; k++)
				printf( "%d\t",frames[k]);
			printf( "| F");
		}
		else
		{
			printf( "|");
			for (int k = 1; k <= frame; k++)
				printf( "%d\t",frames[k]);
			printf( "|");
		}
		printf( "\n");
	}
	printf( "So trang loi la: %d\n", count);
}



// Tim vi tri thay LRU
int TimViTriLRU(int i, int n, int frame, int *a, int *frames)
{
	for (int f = 1; f <= frame; f++)
	{
		if (frames[f] == -1)
			return f;
	}
	int value = -1;
	int m[50];
	for (int z = 0; z <= 49; z++)
	{
		m[z] = -1;
	}
	int dem = 0;
	for (int z = i - 1; z >= 1; z--)
	{
		if (m[a[z]] == -1)
		{
			m[a[z]] = a[z];
			dem++;
			if (dem == frame)
			{
				value = a[z];
				break;
			}
		}
	}
	for (int n = 1; n <= frame; n++)
	{
		if (value == frames[n])
			return n;
	}
}
// Ham LRU
void LRU(int n, int frame, int *a, int *frames)
{
	for (int i = 1; i <= frame; i++)
		frames[i] = -1;
	int k, available, count = 0;

	printf( " Chuoi\t|Khung trang");
	for (k = 0; k<frame - 1; k++)
		printf( "\t");
	printf( "|\n");

	for (int i = 1; i <= n; i++)
	{
		printf( " %d \t", a[i]);
		available = 0; 
		for (k = 1; k <= frame; k++)
			if (frames[k] == a[i]) 
				available = 1;
		if (available == 0) 
		{
			int j = TimViTriLRU(i, n, frame, a, frames);
			frames[j] = a[i];
			count++;
			printf( "|");
			for (int k = 1; k <= frame; k++)
				printf( "%d\t",frames[k]);
			printf( "| F");
		}
		else
		{
			printf( "|");
			for (k = 1; k <= frame; k++)
				printf( "%d\t",frames[k]);
			printf( "|");
		}
		printf( "\n");
	}
	printf( "So trang loi la: %d\n",count);
}

void main()
{
	int choose, choose1;
	int n, a[50], frames[10], frame;
	int n1 = 12;
	int b[] = { -1, 0, 2, 1, 6, 4, 0, 1, 0, 3, 1, 2, 1 };
	printf( "--Page Replacement algorithm--\n");
	printf( "  1. Chuoi tham chieu mac dinh.\n");
	printf( "  2. Nhap chuoi tham chieu bang tay\n");
	scanf("%d" ,&choose);
	switch (choose)
	{
	case 1:
	{   n = n1;
	for (int i = 1; i <= n1; i++)
		a[i] = b[i];
	}; break;
	case 2:
        {
          printf("\nNhap so phan tu chuoi tham chieu: \n");
	  scanf("%d",&n);
	  printf( "\nNhap vao chuoi tham chieu: \n");
	  for (int i = 1; i <= n; i++)
		scanf("%d", &a[i]);
        }; break;
	default: printf( "lua chon khong hop le\n");
	}
	printf( "--Page Replacement algorithm--\n");
	printf( "  Nhap so khung trang :\n");
	scanf("%d",&frame);
	printf( "--Page Replacement algorithm--\n");
	printf( "  1. Giai thuat FIFO\n");
	printf( "  2. Giai thuat OTP(optimal)\n");
	printf( "  3. Giai thuat LRU\n");
	scanf("%d", &choose1);
	switch (choose1)
	{
	case 1:FIFO(n, frame, a, frames); break;
	case 2:OTP(n, frame, a, frames); break;
	case 3:LRU(n, frame, a, frames); break;
	default: printf( "lua chon khong hop le\n");
	}
}

